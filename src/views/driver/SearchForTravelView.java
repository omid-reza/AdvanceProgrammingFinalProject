package views.driver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.List;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.events.FailLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.FrameLoadEvent;
import com.teamdev.jxbrowser.chromium.events.LoadEvent;
import com.teamdev.jxbrowser.chromium.events.LoadListener;
import com.teamdev.jxbrowser.chromium.events.ProvisionalLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.StartLoadingEvent;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import controller.travel.TravelController;
import server.SendToServer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import classes.Driver;
import classes.Travel;
import java.awt.Font;


public class SearchForTravelView {
	private ArrayList<Travel> travels=new ArrayList<Travel>();
	private JFrame frame;
	private static JFrame BackFrame;
	private static Driver driver;
	private JLabel background;
	
	public static void main(String[] strings, JFrame frame2,Driver driver) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchForTravelView.driver=driver;
					BackFrame=frame2;
					SearchForTravelView window = new SearchForTravelView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}

	public SearchForTravelView() {
		AddTravelTotravelArray();
		initialize();
	}

	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(0, 0, 2000, 1050);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnReturn = new JButton("return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BackFrame.setVisible(true);
				frame.setVisible(false);
			}
		});
		
		try {
			BufferedImage img = ImageIO.read(new File("assets\\driver\\profile\\background.jpg"));
			background = new JLabel(new ImageIcon(img));
			background.setBounds(0, 0, frame.getWidth(), frame.getHeight());
			frame.getContentPane().add(background);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		btnReturn.setBounds(12, 13, 97, 25);
		
		
		final Browser browser = new Browser();
		BrowserView browserView = new BrowserView(browser);
		browserView.setBounds(350, 40, 1500, 900);
		
		
		
		List idlist = new List();
		idlist.setFont(new Font("Dialog", Font.BOLD, 13));
		idlist.setBounds(12, 89, 97, 854);
		
		
		JLabel lblId = new JLabel("id :");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblId.setBounds(12, 60, 100, 30);
		lblId.setForeground(Color.WHITE);
		
		
		List selectList = new List();
		selectList.setFont(new Font("Dialog", Font.BOLD, 13));
		selectList.setBounds(234, 89, 110, 854);
		
		List costList = new List();
		costList.setFont(new Font("Dialog", Font.BOLD, 13));
		costList.setBounds(115, 89, 110, 854);
		costList.setEnabled(false);
		
		
		JLabel lblCost = new JLabel("cost :");
		lblCost.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblCost.setBounds(115, 60, 100, 30);
		lblCost.setForeground(Color.WHITE);
		
		background.add(btnReturn);
		background.add(browserView, BorderLayout.CENTER);
		background.add(idlist);
		background.add(lblId);
		background.add(selectList);
		background.add(costList);
		background.add(lblCost);
		//----------add items to lists
		for(int i=0;i<travels.size();i++) {
			idlist.add(travels.get(i).getId()+"");
			costList.add(travels.get(i).getCost()+"");
			selectList.add("select");
		}
		//----------
		browser.addLoadListener(new LoadListener() {
			@Override public void onStartLoadingFrame(StartLoadingEvent arg0) {}
			@Override public void onProvisionalLoadingFrame(ProvisionalLoadingEvent arg0) {}
			@Override public void onFailLoadingFrame(FailLoadingEvent arg0) {}
			@Override public void onDocumentLoadedInMainFrame(LoadEvent arg0) {}
			@Override public void onDocumentLoadedInFrame(FrameLoadEvent arg0) {}
			@Override
			public void onFinishLoadingFrame(FinishLoadingEvent arg0) {
				browser.executeJavaScript("calcRoute("+travels.get(idlist.getSelectedIndex()).getFrom_place_latitude()+","+travels.get(idlist.getSelectedIndex()).getFrom_place_longitude()+","+travels.get(idlist.getSelectedIndex()).getTo_place_lltitude()+","+travels.get(idlist.getSelectedIndex()).getTo_place_longitude()+");");
			}
		});
		selectList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				idlist.select(selectList.getSelectedIndex());
				costList.select(selectList.getSelectedIndex());
				SendToServer.send("http://localhost:8000/travel/AcceptTravelByDriver&travel="+travels.get(idlist.getSelectedIndex()).getId()+"&driver="+driver.getId());
				JOptionPane.showMessageDialog(frame, "Time to take passenger", "selected", JOptionPane.INFORMATION_MESSAGE);
				frame.setVisible(false);
				BackFrame.setVisible(true);
			}
		});
		idlist.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				selectList.select(idlist.getSelectedIndex());
				costList.select(idlist.getSelectedIndex());
				browser.loadURL("C:\\Users\\aa\\Desktop\\Advance Java Projects\\Snap\\src\\views\\map\\showPlace.html");
			}
		});
	}
	
	private void AddTravelTotravelArray() {
		ResultSet resultSet=TravelController.findPassengers();
		try {
			while(resultSet.next()) {
				Travel traveltoadd=new Travel(resultSet.getInt("id"), resultSet.getDouble("from_place_lltitude"), resultSet.getDouble("from_place_longitude"), resultSet.getDouble("to_place_lltitude"), resultSet.getDouble("to_place_longitude"),resultSet.getFloat("cost"));
				travels.add(traveltoadd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
