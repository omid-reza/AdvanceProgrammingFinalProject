package views.passenger;

import java.awt.Font;
import java.awt.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jniwrapper.ac;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.events.FailLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.FrameLoadEvent;
import com.teamdev.jxbrowser.chromium.events.LoadEvent;
import com.teamdev.jxbrowser.chromium.events.LoadListener;
import com.teamdev.jxbrowser.chromium.events.ProvisionalLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.StartLoadingEvent;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import classes.Driver;
import classes.Passenger;
import classes.Travel;
import server.SendToServer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
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
	private static Passenger passenger;
	private JLabel background;
	private ArrayList<Travel> travels=new ArrayList<Travel>();

	//RUNABBLE MAIN METHOD :
	public static void main(String[] args, JFrame frame,Passenger user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					passenger=user;
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


		//SET ATT TO OBJECTS :
		frame.setBounds(0, 0, 2000, 1050);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		try {
			BufferedImage img = ImageIO.read(new File("assets\\passenger\\travels\\background.jpg"));
			background = new JLabel(new ImageIcon(img));
			background.setBounds(0, 0, frame.getWidth(), frame.getHeight());
			frame.getContentPane().add(background);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		final Browser browser = new Browser();
		BrowserView browserView = new BrowserView(browser);
		browserView.setBounds(800, 100, 1000, 850);
		
		
		List idList = new List(),
				statusList = new List(),
				costList = new List(),
				pointList = new List();
		
		JLabel label = new JLabel("id :"),
				label_2 = new JLabel("status :"),
				lblPoint = new JLabel("point :"),
				lblCost = new JLabel("cost :");
		
		
		btnReturn.setBounds(58, 24, 97, 25);
		
		idList.setFont(new Font("Dialog", Font.BOLD, 15));
		idList.setBounds(29, 116, 125, 820);
				
		label.setFont(new Font("Dialog", Font.BOLD, 25));
		
		label.setBounds(29, 80, 150, 30);
		
		
		statusList.setEnabled(false);
		statusList.setFont(new Font("Dialog", Font.BOLD, 15));
		statusList.setBounds(173, 114, 125, 820);
				
				label_2.setFont(new Font("Dialog", Font.BOLD, 25));
				label_2.setBounds(173, 78, 150, 30);
				
				pointList.setFont(new Font("Dialog", Font.BOLD, 15));
				pointList.setEnabled(false);
				pointList.setBounds(317, 114, 125, 820);
				
				lblPoint.setFont(new Font("Tahoma", Font.BOLD, 25));
				lblPoint.setBounds(317, 78, 150, 30);
						
						costList.setFont(new Font("Dialog", Font.BOLD, 15));
						costList.setEnabled(false);
						costList.setBounds(462, 114, 125, 820);
						
						lblCost.setFont(new Font("Tahoma", Font.BOLD, 25));
						lblCost.setBounds(462, 78, 150, 30);
						
						JLabel lblActions = new JLabel("actions:");
						lblActions.setFont(new Font("Tahoma", Font.BOLD, 25));
						lblActions.setBounds(619, 83, 127, 31);
						
						
						List actionlist = new List();
						actionlist.setFont(new Font("Dialog", Font.BOLD, 15));
						actionlist.setBounds(621, 115, 125, 820);				
						
						
		background.add(btnReturn);
		background.add(label_2);
		background.add(pointList);
		background.add(costList);
		background.add(lblPoint);
		background.add(lblCost);
		background.add(idList);
		background.add(label);
		background.add(statusList);
		background.add(lblActions);
		background.add(actionlist);
		background.add(browserView, BorderLayout.CENTER);

		for(int i=0;i<travels.size();i++) {
			idList.add(travels.get(i).getId()+"");
			costList.add(travels.get(i).getCost()+"");
			pointList.add(travels.get(i).getPoint()+"");
			statusList.add(travels.get(i).getStatus());
			if(travels.get(i).getStatus().equals("wait") || travels.get(i).getStatus().equals("comming")) {
				actionlist.add("cancel");
			}else if(travels.get(i).getStatus().equals("finished") && travels.get(i).getPoint()==0) {
				actionlist.add("set point");
			}else if(travels.get(i).getStatus().equals("inprogress")){
				actionlist.add("finish it");
			}else {
				actionlist.add("");
			}
		}
		
		//EVENTS :
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				parentframe.setVisible(true);
			}
		});
		actionlist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int point;
				if(actionlist.getSelectedItem().equals("cancel")) {
					if(JOptionPane.showConfirmDialog(frame, "are you sure?")==0) {
						idList.select(actionlist.getSelectedIndex());
						SendToServer.send("http://localhost:8000/travel/cancel&travel_id="+idList.getSelectedItem());
					}
				}else if(actionlist.getSelectedItem().equals("set point")) {
					String input=JOptionPane.showInputDialog(frame, "type your point", "set point", JOptionPane.QUESTION_MESSAGE);
					point=Integer.parseInt(input);
					idList.select(actionlist.getSelectedIndex());
					SendToServer.send("http://localhost:8000/passenger/setpoint&id="+idList.getSelectedItem()+"&point="+point);
				}else if(actionlist.getSelectedItem().equals("finish it")){
					idList.select(actionlist.getSelectedIndex());
					if(SendToServer.send("http://localhost:8000/travel/setfinish&travel="+idList.getSelectedItem()+"&driver="+travels.get(idList.getSelectedIndex()).getDriver().getId()).equals("1")){
						JOptionPane.showMessageDialog(frame, "travel finished", "finished", JOptionPane.INFORMATION_MESSAGE);
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
		idList.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				statusList.select(idList.getSelectedIndex());
				pointList.select(idList.getSelectedIndex());
				costList.select(idList.getSelectedIndex());
				actionlist.select(idList.getSelectedIndex());
				browser.loadURL("C:\\Users\\aa\\Desktop\\Advance Java Projects\\Snap\\src\\views\\map\\showPlace.html");
			}
		});
	}
	private void AddTravelTotravelArray() {
		try {
			String jsonString = SendToServer.send("http://localhost:8000/travel/getPassengerTravels&id="+passenger.getId());;
			JSONObject jsonResult = new JSONObject(jsonString);
			JSONArray data = jsonResult.getJSONArray("data");
			if(data != null) {
			    for(int i = 0 ; i < data.length() ; i++) {
			    	JSONObject t=(JSONObject) data.get(i);
			    	Travel traveltoadd=new Travel(t.getInt("id"), t.getDouble("from_place_lltitude"), t.getDouble("from_place_longitude"), t.getDouble("to_place_lltitude"), t.getDouble("to_place_longitude"), t.getInt("cost"));
			    	traveltoadd.setStatus(t.getString("status"));
			    	try {
			    		traveltoadd.setDriver(new Driver(t.getInt("driver_id")));
			    	}catch (Exception e) {
			    		traveltoadd.setDriver(null);
					}
			    	traveltoadd.setPoint(t.getInt("point"));
			    	travels.add(traveltoadd);
			    }
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
