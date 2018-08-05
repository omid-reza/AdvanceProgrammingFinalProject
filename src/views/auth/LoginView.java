package views.auth;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

import classes.Driver;
import classes.Passenger;
import classes.User;
import controller.driver.DriverController;
import controller.driver.SalarayController;
import controller.passenger.PassengerController;
import server.SendToServer;

import java.awt.Color;

public final class LoginView {
	//CREATE GLOBAL VARIABLES AND OBJECTS:
	private JFrame frame;
	private JTextField username;
	private JPasswordField passwordField;
	private JLabel background;
	private JButton btnLogin,btnSignup;
	
	//RUNABBLE MAIN METHOD :
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView window = new LoginView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginView() {
		initialize();
	}

	private void initialize() {
		// CREATE OBJECTS :
		frame = new JFrame("Snap - Login");
		
		JLabel lblPassword = new JLabel("password : "),
				   lblDontHaveAccount = new JLabel("Don't have account ? "),
				   lblUsername = new JLabel("Username :");

		passwordField = new JPasswordField();

		JCheckBox hideOrShowPasswordCheckbox = new JCheckBox("show");
	
		//SET ATT TO OBJECTS :
		frame.setBounds(0, 0, 2000, 1050);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		username = new JTextField();

		try {
			BufferedImage img = ImageIO.read(new File("assets\\Login\\LoginBackGround.jpg"));
			background = new JLabel(new ImageIcon(img));
			background.setBounds(0, 0, frame.getWidth(), frame.getHeight());
			frame.getContentPane().add(background);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		username.setForeground(Color.WHITE);
		username.setFont(new Font("Tahoma", Font.BOLD, 20));
		username.setColumns(10);
		username.setBounds(900, 392, 250, 40);
		username.setBackground(Color.decode("#828282"));
		username.setBorder(null);

		lblDontHaveAccount.setBounds(800, 900, 220, 30);
		lblDontHaveAccount.setFont(new Font("Tahoma", Font.BOLD, 20));

		passwordField.setBounds(900, 500, 250, 40);
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 20));
		passwordField.setForeground(Color.WHITE);
		passwordField.setBackground(Color.decode("#828282"));
		passwordField.setBorder(null);

		hideOrShowPasswordCheckbox.setBounds(1200, 500, 113, 25);
		hideOrShowPasswordCheckbox.setOpaque(false);
		hideOrShowPasswordCheckbox.setContentAreaFilled(false);
		hideOrShowPasswordCheckbox.setBorderPainted(false);
		hideOrShowPasswordCheckbox.setForeground(Color.decode("#414443"));
		hideOrShowPasswordCheckbox.setFont(new Font("Tahoma", Font.BOLD, 20));

		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblPassword.setForeground(Color.decode("#414443"));
		lblPassword.setBounds(650, 500, 200, 30);
		
		try {
			BufferedImage Log = ImageIO.read(new File("assets\\Login\\LoginBtn.png"));
			btnLogin = new JButton();
			btnLogin.setIcon(new ImageIcon(Log));
			btnLogin.setBounds(820, 520, 300, 200);
			btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnLogin.setOpaque(false);
			btnLogin.setContentAreaFilled(false);
			btnLogin.setBorderPainted(false);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		try {
			BufferedImage signup = ImageIO.read(new File("assets\\Login\\SignUpBtn.png"));
			btnSignup = new JButton();
			btnSignup.setIcon(new ImageIcon(signup));
			btnSignup.setBounds(950, 820, 300, 200);
			btnSignup.setOpaque(false);
			btnSignup.setContentAreaFilled(false);
			btnSignup.setBorderPainted(false);
			btnSignup.setFont(new Font("Tahoma", Font.BOLD, 15));
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
		
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblUsername.setForeground(Color.decode("#414443"));
		lblUsername.setBounds(650, 400, 200, 30);

		//ADD OBJECTS TO FRAME :
		background.add(username);
		background.add(btnLogin);
		background.add(btnSignup);
		background.add(passwordField);
		background.add(lblDontHaveAccount);
		background.add(hideOrShowPasswordCheckbox);
		background.add(lblUsername);
		background.add(lblPassword);
		frame.getContentPane().setLayout(null);
		
		//EVENTS :
		hideOrShowPasswordCheckbox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		        	passwordField.setEchoChar((char) 0);
		        } else {
		        	passwordField.setEchoChar('•');
		        }
				
			}
		});

		
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (username.getDocument().getLength() == 0 || passwordField.getDocument().getLength() == 0) {
					JOptionPane.showMessageDialog(frame, "username and password cant be empty", "empty input", JOptionPane.ERROR_MESSAGE);
					return;
				}
			
				if(SendToServer.send("http://localhost:8000/passenger/login&username="+username.getText()+"&password="+passwordField.getText()).equals("1")) {
					// passenger :
					frame.setVisible(false);
					passengerLog(new User(username.getText(), passwordField.getText()));
					return;
				}else if(SendToServer.send("http://localhost:8000/driver/login&username="+username.getText()+"&password="+passwordField.getText()).equals("1")) {
					// driver :
					frame.setVisible(false);
					driverLog(new User(username.getText(), passwordField.getText()));
					return;
				}
				JOptionPane.showMessageDialog(frame, "Username Or Password is Wrong!","incorrect user or pass",JOptionPane.ERROR_MESSAGE);
			}

			private void passengerLog(User user) {
				Passenger passenger=new Passenger(user.getUserName(), user.getPassword());
				int id=Integer.parseInt(SendToServer.send("http://localhost:8000/passenger/getIdByUsername&username="+user.getUserName()));
				passenger.setId(id);
				views.passenger.ProfileView.main(new String[] {}, passenger);	
			}

			private void driverLog(User user) {
					int id=Integer.parseInt(SendToServer.send("http://localhost:8000/driver/getIdByUsername&username="+user.getUserName()));
					user.setId(id);
					Driver driver=new Driver(user.getUserName(), user.getPassword(),SalarayController.getSalary(user.getId()));
					driver.setId(user.getId());
					views.driver.ProfileView.main(new String[] {},driver);
			}
		});
		
		btnSignup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				SignupView.main(new String[] {});
			}
		});
		
	}
}
