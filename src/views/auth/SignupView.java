package views.auth;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import controller.auth.SignupController;
import jdk.nashorn.internal.scripts.JO;
import server.SendToServer;
import classes.User;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.Color;

public final class SignupView {

	private JFrame frame;
	private JTextField username = new JTextField();
	private JPasswordField passwordField = new JPasswordField(),
						   passwordField_1 = new JPasswordField();
	private JLabel background;
	private JButton btnSignUp, btnLogin;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignupView window = new SignupView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public SignupView() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Snap - SignUp");
		JLabel lblUsername = new JLabel("username :"),
				lblPassword = new JLabel("password :"),
				lblPasswordConfirm = new JLabel("password confirm :"),
				lblDoYouHave = new JLabel("Do You have account ?");
		
		try {
			BufferedImage img = ImageIO.read(new File("assets\\SignUp\\SignUp.jpg"));
			background = new JLabel(new ImageIcon(img));
			background.setBounds(0, 997, 1920, -997);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		JComboBox<String> passengerOrDriver = new JComboBox<String>();
		
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblUsername.setForeground(Color.decode("#414443"));
		lblUsername.setBounds(600, 300, 200, 30);
		
		lblDoYouHave.setBounds(700, 900, 300, 30);
		lblDoYouHave.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblPassword.setForeground(Color.decode("#414443"));
		lblPassword.setBounds(600, 400, 200, 30);
		
		lblPasswordConfirm.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblPasswordConfirm.setForeground(Color.decode("#414443"));
		lblPasswordConfirm.setBounds(550, 500, 300, 30);
		
		try {
			BufferedImage Log = ImageIO.read(new File("assets\\Login\\SignUpBtn.png"));
			btnSignUp= new JButton();
			btnSignUp.setIcon(new ImageIcon(Log));
			btnSignUp.setBounds(820, 520, 300, 200);
			btnSignUp.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnSignUp.setOpaque(false);
			btnSignUp.setContentAreaFilled(false);
			btnSignUp.setBorderPainted(false);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			BufferedImage signup = ImageIO.read(new File("assets\\Login\\LoginBtn.png"));
			btnLogin = new JButton();
			btnLogin.setIcon(new ImageIcon(signup));
			btnLogin.setBounds(950, 820, 300, 200);
			btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnLogin.setOpaque(false);
			btnLogin.setContentAreaFilled(false);
			btnLogin.setBorderPainted(false);
			btnLogin.setForeground(SystemColor.desktop);
			btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		username.setForeground(Color.WHITE);
		username.setFont(new Font("Tahoma", Font.BOLD, 20));
		username.setColumns(10);
		username.setBounds(900, 292, 250, 40);
		username.setBackground(Color.decode("#828282"));
		username.setBorder(null);

		
		passwordField.setBounds(900, 400, 250, 40);
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 20));
		passwordField.setForeground(Color.WHITE);
		passwordField.setBackground(Color.decode("#828282"));
		passwordField.setBorder(null);
		
		passwordField_1.setBounds(900, 500, 250, 40);
		passwordField_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		passwordField_1.setForeground(Color.WHITE);
		passwordField_1.setBackground(Color.decode("#828282"));
		passwordField_1.setBorder(null);;

		passengerOrDriver.addItem("Passenger");
		passengerOrDriver.addItem("Driver");
		passengerOrDriver.setBounds(1250, 400, 200, 30);
		passengerOrDriver.setFont(new Font("Tahoma", Font.BOLD, 20));
		passengerOrDriver.setMaximumRowCount(2);
		
		frame.setBounds(0, 0, 2000, 1050);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(background);
		
		background.add(lblUsername);
		background.add(lblPasswordConfirm);
		background.add(btnSignUp);
		background.add(lblDoYouHave);
		background.add(btnLogin);
		background.add(lblPassword);
		background.add(username);
		background.add(passwordField);
		background.add(passwordField_1);
		background.add(passengerOrDriver);

		// EVENTS :
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				LoginView.main(new String[] {});
				
			}
		});
		
		btnSignUp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (username.getDocument().getLength() == 0 || passwordField.getDocument().getLength() ==0 || passwordField_1.getDocument().getLength() == 0) {
					JOptionPane.showMessageDialog(frame, "username and password and password confirm should'n empty", "empty input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if ( ! (passwordField.getText().toString().equals(passwordField_1.getText().toString()))) {
					JOptionPane.showMessageDialog(frame, "password are not same", "no equal passwords", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String kind=null;
				if (passengerOrDriver.getSelectedItem().toString().equals("Passenger")) {
					kind="passenger";
				}else { // passengerOrDriver.getSelectedIndex()..equals("driver")
					kind="driver";
				}
				
				if(SendToServer.send("http://localhost:8000/signup&username="+username.getText()+"&password="+ passwordField.getText()+"&kind="+kind).equals("1")) {
					JOptionPane.showMessageDialog(frame, "Account created", "account message", JOptionPane.INFORMATION_MESSAGE);
					frame.setVisible(false);
					LoginView.main(new String[] {});
					return;
				}
				JOptionPane.showMessageDialog(frame, "chosee another username", "exist username", JOptionPane.ERROR_MESSAGE);
			}
		});
	}
}
