package views.driver;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;

import classes.Driver;
import controller.driver.DriverController;
import server.SendToServer;
import views.auth.LoginView;
import views.driver.TravelsView;

import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public final class ProfileView {

	private JFrame frame;
	private static Driver driver;
	private JLabel background;
	
	public static void main(String[] args, Driver user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					driver =  user;
					ProfileView window = new ProfileView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ProfileView() {
		initialize();
	}


	private void initialize() {
		//CREATE OBJECTS :
		frame = new JFrame();
		JDesktopPane desktopPane = new JDesktopPane(),
					 desktopPane_1 = new JDesktopPane();
		
		JButton btnLogout = new JButton("logout"), 
				btnSearchForTravel = new JButton("search For Travel"),
				travelView=new JButton("see all");

		JLabel salary = new JLabel(),
				lblSalary = new JLabel("salary :"),
				lblUsername = new JLabel("username :"),
				canceled = new JLabel(),
				finished = new JLabel(),
				inprogress = new JLabel(),
				lblCanceld = new JLabel("canceled :"),
				lblFinished = new JLabel("finished :"),
				lblInProgress = new JLabel("in progress :"),
				username = new JLabel();
		//SET ATT TO OBJECTS :
		frame.setBounds(0, 0, 2000, 1050);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		try {
			BufferedImage img = ImageIO.read(new File("assets\\driver\\profile\\background.jpg"));
			background = new JLabel(new ImageIcon(img));
			background.setBounds(0, 0, frame.getWidth(), frame.getHeight());
			frame.getContentPane().add(background);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		desktopPane.setBounds(35, 850, 500, 130);
		desktopPane.setOpaque(false);
		desktopPane.setBorder(null);
		
		
		travelView.setBounds(1700, 20, 100, 50);
		travelView.setText("see all");
		
		salary.setText(driver.getSalary()+"");
		salary.setBounds(30, 55, 136, 16);
		salary.setFont(new Font("Tahoma", Font.BOLD, 20));
		salary.setForeground(Color.WHITE);
				
		lblSalary.setBounds(27, 790, 200, 30);
		lblSalary.setForeground(Color.WHITE);
		lblSalary.setFont(new Font("Tahoma", Font.BOLD, 28));

		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblUsername.setBounds(27, 46, 200, 30);
		
		desktopPane_1.setBounds(1100, 100, 700, 500);
		desktopPane_1.setOpaque(false);
		desktopPane.setBorder(null);
				
		canceled.setText(SendToServer.send("http://localhost:8000/driver/travelkindsCount&id="+driver.getId()+"&kind=canceled"));
		canceled.setBounds(170, 100, 300, 35);
		canceled.setForeground(Color.WHITE);
		canceled.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		finished.setText(SendToServer.send("http://localhost:8000/driver/travelkindsCount&id="+driver.getId()+"&kind=finished"));
		finished.setBounds(170, 300, 200, 150);
		finished.setForeground(Color.WHITE);
		finished.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		inprogress.setText(SendToServer.send("http://localhost:8000/driver/travelkindsCount&id="+driver.getId()+"&kind=inprogress"));
		inprogress.setBounds(170, 252, 250, 35);
		inprogress.setForeground(Color.WHITE);
		inprogress.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		JLabel lblComming = new JLabel("comming :");
		lblComming.setForeground(Color.WHITE);
		lblComming.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblComming.setBounds(28, 186, 106, 25);
		desktopPane_1.add(lblComming);
		
		JLabel label = new JLabel(SendToServer.send("http://localhost:8000/driver/travelkindsCount&id="+driver.getId()+"&kind=comming"));
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 30));
		label.setBounds(170, 182, 250, 25);
		desktopPane_1.add(label);
		
		
		try {
			BufferedImage pic = ImageIO.read(new File("assets\\driver\\profile\\picktravel.png"));
			btnSearchForTravel = new JButton();
			btnSearchForTravel.setIcon(new ImageIcon(pic));
			btnSearchForTravel.setBounds(520, 770, 300, 200);
			btnSearchForTravel.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnSearchForTravel.setOpaque(false);
			btnSearchForTravel.setContentAreaFilled(false);
			btnSearchForTravel.setBorderPainted(false);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		lblCanceld.setBounds(28, 100, 300, 35);
		lblCanceld.setForeground(Color.WHITE);
		lblCanceld.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		lblFinished.setBounds(28, 300, 200, 150);
		lblFinished.setForeground(Color.WHITE);
		lblFinished.setFont(new Font("Tahoma", Font.BOLD, 20));

		lblInProgress.setBounds(28, 252, 250, 35);
		lblInProgress.setForeground(Color.WHITE);
		lblInProgress.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		username.setText(driver.getUserName());
		username.setForeground(Color.WHITE);
		username.setFont(new Font("Tahoma", Font.BOLD, 30));
		username.setBounds(215, 43, 200, 30);
		
		try {
			BufferedImage logout = ImageIO.read(new File("assets\\passenger\\profile\\LogoutBtn.png"));
			btnLogout = new JButton();
			btnLogout.setIcon(new ImageIcon(logout));
			btnLogout.setBounds(1600, 800, 300, 170);
			btnLogout.setFont(new Font("Tahoma", Font.BOLD, 25));
			btnLogout.setOpaque(false);
			btnLogout.setContentAreaFilled(false);
			btnLogout.setBorderPainted(false);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		
		

		desktopPane.add(salary);

		desktopPane_1.add(canceled);
		desktopPane_1.add(finished);
		desktopPane_1.add(inprogress);
		desktopPane_1.add(lblCanceld);
		desktopPane_1.add(lblFinished);
		desktopPane_1.add(lblInProgress);

		//EVENTS :
		background.add(desktopPane);
		background.add(lblSalary);
		background.add(lblUsername);
		background.add(desktopPane_1);
		background.add(username);
		background.add(btnLogout);
		background.add(btnSearchForTravel);
		background.add(travelView);
		
		travelView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				TravelsView.main(new String[] {}, frame, driver.getId());		
			}
		});
		
		btnSearchForTravel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				SearchForTravelView.main(new String[] {},frame,driver);
			}
		});
		
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				LoginView.main(new String[] {});
			}
		});
		
	}
}
