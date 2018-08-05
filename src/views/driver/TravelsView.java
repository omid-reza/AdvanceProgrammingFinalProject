package views.driver;

import java.awt.Font;
import java.awt.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.events.FailLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.FrameLoadEvent;
import com.teamdev.jxbrowser.chromium.events.LoadEvent;
import com.teamdev.jxbrowser.chromium.events.LoadListener;
import com.teamdev.jxbrowser.chromium.events.ProvisionalLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.StartLoadingEvent;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import classes.Travel;
import controller.travel.TravelController;
import server.SendToServer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;


public class TravelsView {

	//CREATE GLOBAL OBJECTS AND VARIABLES:
	private JFrame frame;
	private static JFrame parentframe;
	private static int driver_id;
	private JLabel background;
	private ArrayList<Travel> travels=new ArrayList<Travel>();

	//RUNABBLE MAIN METHOD :
	public static void main(String[] args, JFrame frame,int id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					driver_id=id;
					parentframe=frame;
					TravelsView window = new TravelsView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TravelsView() {
		AddTravelTotravelArray();
		initialize();
	}

	private void initialize() {
		//CCREATE OBJECTS :
		frame = new JFrame();
		
		JButton btnReturn = new JButton("return");
		
		List idList = new List(),
			driverList = new List(),
			statusList = new List(),
			costList = new List(),
			pointList = new List(),
			actionlist=new List();
		
		JLabel lblPoint = new JLabel("point :"),
			lblCost = new JLabel("cost :"),
			lblid = new JLabel("id :"),
			lblstatus = new JLabel("status :"),
			lblaction=new JLabel("action :");


		//SET ATT TO OBJECTS :
		frame.setBounds(0, 0, 2000, 1050);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		try {
			BufferedImage img = ImageIO.read(new File("assets\\driver\\TravelView\\background.jpg"));
			background = new JLabel(new ImageIcon(img));
			background.setBounds(0, 0, frame.getWidth(), frame.getHeight());
			frame.getContentPane().add(background);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		for(int i=0;i<travels.size();i++) {
			idList.add(travels.get(i).getId()+"");
			costList.add(travels.get(i).getCost()+"");
			pointList.add(travels.get(i).getPoint()+"");
			statusList.add(travels.get(i).getStatus());
			if(travels.get(i).getStatus().equals("wait") || travels.get(i).getStatus().equals("comming")) {
				actionlist.add("cancel");
			}else{
				actionlist.add("");
			}
		}
		
		
		final Browser browser = new Browser();
		BrowserView browserView = new BrowserView(browser);
		browserView.setBounds(725, 120, 1150, 850);
		
		btnReturn.setBounds(58, 24, 97, 25);
		
		idList.setFont(new Font("Dialog", Font.BOLD, 15));
		idList.setBounds(29, 116, 125, 850);
		
		statusList.setEnabled(false);
		statusList.setFont(new Font("Dialog", Font.BOLD, 15));
		statusList.setBounds(160, 116, 125, 850);
				
		lblid.setFont(new Font("Dialog", Font.BOLD, 25));
		lblid.setBounds(29, 80, 70, 24);
		lblid.setForeground(Color.WHITE);
		
		lblstatus.setFont(new Font("Dialog", Font.BOLD, 25));
		lblstatus.setBounds(160, 80, 107, 24);
		lblstatus.setForeground(Color.WHITE);
		
		lblPoint.setFont(new Font("Dialog", Font.BOLD, 25));
		lblPoint.setBounds(291, 80, 100, 30);
		lblPoint.setForeground(Color.WHITE);
		
		pointList.setFont(new Font("Dialog", Font.BOLD, 15));
		pointList.setEnabled(false);
		pointList.setBounds(291, 116, 125, 850);
		
		
		lblCost.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblCost.setBounds(422, 80, 100, 35);
		lblCost.setForeground(Color.WHITE);
		
		costList.setFont(new Font("Dialog", Font.BOLD, 15));
		costList.setEnabled(false);
		costList.setBounds(422, 116, 125, 850);
		
		lblaction.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblaction.setBounds(555,80,100,30);
		lblaction.setForeground(Color.WHITE);
		
		actionlist.setBounds(555,116,140,850);
		actionlist.setFont(new Font("Dialog", Font.BOLD, 15));
		
		
		background.add(btnReturn);
		background.add(idList);
		background.add(driverList);
		background.add(statusList);
		background.add(lblid);
		background.add(lblstatus);
		background.add(pointList);
		background.add(lblPoint);
		background.add(costList);
		background.add(lblCost);
		background.add(lblaction);
		background.add(actionlist);
		background.add(browserView, BorderLayout.CENTER);
		
		
		//EVENTS :
		actionlist.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				idList.select(actionlist.getSelectedIndex());
				costList.select(actionlist.getSelectedIndex());
				pointList.select(actionlist.getSelectedIndex());
				statusList.select(actionlist.getSelectedIndex());	
			}
		});
		actionlist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(actionlist.getSelectedItem().equals("cancel")) {
					if(JOptionPane.showConfirmDialog(frame, "are you sure?")==0) {
						idList.select(actionlist.getSelectedIndex());
						SendToServer.send("http://localhost:8000/travel/cancel&travel_id="+idList.getSelectedItem());
					}
				}
				
			}
		});
		browser.addLoadListener(new LoadListener() {
			@Override public void onStartLoadingFrame(StartLoadingEvent arg0) {}
			@Override public void onProvisionalLoadingFrame(ProvisionalLoadingEvent arg0) {}
			@Override public void onFailLoadingFrame(FailLoadingEvent arg0) {}
			@Override public void onDocumentLoadedInMainFrame(LoadEvent arg0) {}
			@Override public void onDocumentLoadedInFrame(FrameLoadEvent arg0) {}
			
			@Override
			public void onFinishLoadingFrame(FinishLoadingEvent arg0) {
				browser.executeJavaScript("calcRoute("+travels.get(idList.getSelectedIndex()).getFrom_place_latitude()+","+travels.get(idList.getSelectedIndex()).getFrom_place_longitude()+","+travels.get(idList.getSelectedIndex()).getTo_place_lltitude()+","+travels.get(idList.getSelectedIndex()).getTo_place_longitude()+");");
			}
			
		});
		
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				parentframe.setVisible(true);
			}
		});
	
		idList.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				statusList.select(idList.getSelectedIndex());
				driverList.select(idList.getSelectedIndex());
				pointList.select(idList.getSelectedIndex());
				costList.select(idList.getSelectedIndex());
				actionlist.select(idList.getSelectedIndex());
				browser.loadURL("C:\\Users\\aa\\Desktop\\Advance Java Projects\\Snap\\src\\views\\map\\showPlace.html");
			}
		});
		
	}
	private void AddTravelTotravelArray() {
		try {
			
			String jsonString = SendToServer.send("http://localhost:8000/travel/getDriverTravels&id="+driver_id);;
			JSONObject jsonResult = new JSONObject(jsonString);
			JSONArray data = jsonResult.getJSONArray("data");
			if(data != null) {
			    for(int i = 0 ; i < data.length() ; i++) {
			    	JSONObject t=(JSONObject) data.get(i);
			    	Travel traveltoadd=new Travel(t.getInt("id"), t.getDouble("from_place_lltitude"), t.getDouble("from_place_longitude"), t.getDouble("to_place_lltitude"), t.getDouble("to_place_longitude"), t.getInt("cost"));
			    	traveltoadd.setStatus(t.getString("status"));
			    	traveltoadd.setPoint(t.getInt("point"));
			    	travels.add(traveltoadd);
			    }
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
}
