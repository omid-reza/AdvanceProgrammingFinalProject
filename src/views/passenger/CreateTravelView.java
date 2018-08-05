package views.passenger;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jniwrapper.ba;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.events.ConsoleEvent;
import com.teamdev.jxbrowser.chromium.events.ConsoleListener;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import classes.Travel;
import controller.travel.TravelController;
import server.SendToServer;

import java.awt.Color;

public class CreateTravelView {

	private JFrame frame;
	private static Travel travel=null;
	private static JFrame BackFrame;
	private JLabel background;
	public static void main(String[] args, JFrame frame2, Travel ourTravel) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BackFrame=frame2;
					travel=ourTravel;
					CreateTravelView window = new CreateTravelView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	

	public CreateTravelView() {
		initialize();
	}
	
    

	private void initialize() {

		//CREATE OBJECTS :   
		frame = new JFrame("map.html");
		frame.setBounds(0, 0, 2000, 1050);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnReturn = new JButton("Return"),
				btnCreate = new JButton("Create"),
				btnReselect=new JButton("ReSelect");

		JLabel lblCost = new JLabel("cost :"),
				cost = new JLabel("0");
		
		btnReselect.setVisible(false);
		
		try {
			BufferedImage bg = ImageIO.read(new File("assets\\passenger\\createTravel\\background.jpg"));
			background = new JLabel(new ImageIcon(bg));
			background.setBounds(0, 0, frame.getWidth(), frame.getHeight());
			frame.getContentPane().add(background);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		cost.setBounds(1100, 920, 200, 30);
		cost.setFont(new Font("Tahoma", Font.BOLD, 30));
		cost.setForeground(Color.WHITE);
		
		lblCost.setForeground(Color.WHITE);
		lblCost.setBounds(850, 920, 200, 30);
		lblCost.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		btnReselect.setVisible(false);
		btnReselect.setBounds(600, 920, 200, 30);
		
		btnCreate.setBounds(1700, 934, 97, 25);
		
		btnReturn.setBounds(26, 920, 97, 25);
		
	    final Browser browser = new Browser();
	    BrowserView browserView = new BrowserView(browser);
	    browserView.setBackground(Color.RED);
	    browserView.setBounds(10, 10, 1890, 900);
	    browser.loadURL("C://Users//aa//Desktop//Advance Java Projects//Snap//src//views//map//SelectPlace.html");
		
		// ADD OBJECTS TO FRAME :
	    frame.getContentPane().setLayout(null);
	    background.add(browserView, BorderLayout.CENTER);
	    background.add(btnReturn);
		background.add(lblCost);
		background.add(btnCreate);
		background.add(cost);
		background.add(btnReselect);
		frame.setVisible(true);
		
		//EVENTS :
		btnReselect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				browser.reload();
				btnReselect.setVisible(false);
				travel.setFrom_place_latitude(0);
				travel.setFrom_place_longitude(0);
				travel.setTo_place_lltitude(0);
				travel.setTo_place_longitude(0);
			}
		});
		
		 browser.addConsoleListener(new ConsoleListener() {
				@Override
				public void onMessage(ConsoleEvent message) {
					if (travel.getFrom_place_latitude() == 0) {
						travel.setFrom_place_latitude(Float.parseFloat(message.getMessage()));
					}else if(travel.getFrom_place_longitude() == 0) {
						travel.setFrom_place_longitude(Float.parseFloat(message.getMessage()));
					}else if(travel.getTo_place_lltitude()==0) {
						travel.setTo_place_lltitude(Float.parseFloat(message.getMessage()));
					}else if(travel.getTo_place_longitude()== 0) {
						travel.setTo_place_longitude(Float.parseFloat(message.getMessage()));
						btnReselect.setVisible(true);
					}
				}
		       });
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				BackFrame.setVisible(true);
			}
		});

		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm;
				confirm = JOptionPane.showConfirmDialog(frame, "are you sure?", "Confirm", 0);
				if (confirm == 0) {
					SendToServer.send("http://localhost:8000/travel/create&passenger_id="+travel.getPassenger().getId()+"&cost=0&From_place_latitude="+travel.getFrom_place_latitude()+"&From_place_longitude="+travel.getFrom_place_longitude()+"&To_place_lltitude="+travel.getTo_place_lltitude()+"&To_place_longitude="+travel.getTo_place_longitude());
					JOptionPane.showMessageDialog(frame, "creaeted", "message", JOptionPane.INFORMATION_MESSAGE);
					frame.setVisible(false);
					BackFrame.setVisible(true);
				}
			}
		});
		
	}
}
