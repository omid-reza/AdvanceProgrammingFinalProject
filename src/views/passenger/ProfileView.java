package views.passenger;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import classes.Passenger;
import classes.Travel;
import views.auth.LoginView;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import controller.passenger.MoneyController;
import server.SendToServer;

import javax.swing.JDesktopPane;

public class ProfileView {
	public static Passenger passenger;
	private JFrame frame;
	private JLabel background;
	private JButton btnSeeAll, btnAddMoney;
	private JButton btnLogOut,btnCreateNewTravel;
	public static void main(String[] args, Passenger user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					passenger=user;
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
		//create objects :
		frame = new JFrame();

		JLabel lblUsername = new JLabel("username : "),
				lblTravels = new JLabel("Travels :"),
				lblMoney = new JLabel("Money :"),
				lblFinished = new JLabel("finished :"),
				lblInProgress = new JLabel("in progress :"),
				lblCanceld = new JLabel("canceled :"),
				finished = new JLabel(),
				inprogress = new JLabel(),
				canceled = new JLabel(),
				money = new JLabel(),
				username = new JLabel();

		JDesktopPane desktopPane = new JDesktopPane(),
					desktopPane_1 = new JDesktopPane();

		//SET ATT TO OBJECTS :
		frame.setBounds(0, 0, 2000, 1050);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		try {
			BufferedImage img = ImageIO.read(new File("assets\\passenger\\profile\\Background.jpg"));
			background = new JLabel(new ImageIcon(img));
			background.setBounds(0, 0, frame.getWidth(), frame.getHeight());
			frame.getContentPane().add(background);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblUsername.setBounds(27, 46, 200, 30);
		
		lblTravels.setFont(new Font("Tahoma", Font.BOLD, 30));;
		lblTravels.setForeground(Color.white);
		lblTravels.setBounds(1100, 47, 200, 30);
		

		lblMoney.setBounds(27, 790, 200, 30);
		lblMoney.setForeground(Color.WHITE);
		lblMoney.setFont(new Font("Tahoma", Font.BOLD, 28));
		
		desktopPane.setBounds(1100, 100, 700, 500);
		desktopPane.setOpaque(false);
		desktopPane.setBorder(null);
			
		lblFinished.setBounds(28, 300, 200, 150);
		lblFinished.setForeground(Color.WHITE);
		lblFinished.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		lblInProgress.setBounds(28, 252, 250, 35);
		lblInProgress.setForeground(Color.WHITE);
		lblInProgress.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		lblCanceld.setBounds(28, 100, 300, 35);
		lblCanceld.setForeground(Color.WHITE);
		lblCanceld.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		finished.setText(SendToServer.send("http://localhost:8000/passenger/travelkindsCount&id="+passenger.getId()+"&kind=finished"));
		finished.setBounds(170, 300, 200, 150);
		finished.setForeground(Color.WHITE);
		finished.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		inprogress.setText(SendToServer.send("http://localhost:8000/driver/travelkindsCount&id="+passenger.getId()+"&kind=inprogress"));
		inprogress.setBounds(170, 252, 250, 35);
		inprogress.setForeground(Color.WHITE);
		inprogress.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		canceled.setText(SendToServer.send("http://localhost:8000/passenger/travelkindsCount&id="+passenger.getId()+"&kind=canceled"));
		canceled.setBounds(170, 100, 300, 35);
		canceled.setForeground(Color.WHITE);
		canceled.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		desktopPane_1.setBounds(35, 850, 500, 130);
		desktopPane_1.setOpaque(false);
		desktopPane_1.setBorder(null);

		
		money.setText(SendToServer.send("http://localhost:8000/passenger/getmoney&id="+passenger.getId()));
		money.setBounds(30, 55, 136, 16);
		money.setFont(new Font("Tahoma", Font.BOLD, 20));
		money.setForeground(Color.WHITE);
		
		try {
			BufferedImage addmoneybtn = ImageIO.read(new File("assets\\passenger\\profile\\AddMoneyBtn.png"));
			btnAddMoney = new JButton();
			btnAddMoney.setIcon(new ImageIcon(addmoneybtn));
			btnAddMoney.setBounds(200, 10, 300, 100);
			btnAddMoney.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnAddMoney.setOpaque(false);
			btnAddMoney.setContentAreaFilled(false);
			btnAddMoney.setBorderPainted(false);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			BufferedImage seeall = ImageIO.read(new File("assets\\passenger\\profile\\SeeAllBtn.png"));
			btnSeeAll = new JButton();
			btnSeeAll.setIcon(new ImageIcon(seeall));
			btnSeeAll.setBounds(1500, 20, 230, 65);
			btnSeeAll.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnSeeAll.setOpaque(false);
			btnSeeAll.setContentAreaFilled(false);
			btnSeeAll.setBorderPainted(false);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		try {
			BufferedImage createnewtravel = ImageIO.read(new File("assets\\passenger\\profile\\CreateTravelBtn.png"));
			btnCreateNewTravel = new JButton();
			btnCreateNewTravel.setIcon(new ImageIcon(createnewtravel));
			btnCreateNewTravel.setBounds(1300, 650, 350, 150);
			btnCreateNewTravel.setFont(new Font("Tahoma", Font.BOLD, 25));
			btnCreateNewTravel.setOpaque(false);
			btnCreateNewTravel.setContentAreaFilled(false);
			btnCreateNewTravel.setBorderPainted(false);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			BufferedImage logout = ImageIO.read(new File("assets\\passenger\\profile\\LogOutBtn.png"));
			btnLogOut = new JButton();
			btnLogOut.setIcon(new ImageIcon(logout));
			btnLogOut.setBounds(1600, 800, 300, 170);
			btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 25));
			btnLogOut.setOpaque(false);
			btnLogOut.setContentAreaFilled(false);
			btnLogOut.setBorderPainted(false);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		username.setText(passenger.getUserName());
		username.setForeground(Color.WHITE);
		username.setFont(new Font("Tahoma", Font.BOLD, 30));
		username.setBounds(215, 43, 200, 30);

		desktopPane.add(canceled);
		desktopPane.add(finished);
		desktopPane.add(inprogress);
		desktopPane.add(lblCanceld);
		desktopPane.add(lblFinished);
		desktopPane.add(lblInProgress);
		
		desktopPane_1.add(money);
		desktopPane_1.add(btnAddMoney);

		background.add(desktopPane);
		
		JLabel lblComming = new JLabel("comming :");
		lblComming.setForeground(Color.WHITE);
		lblComming.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblComming.setBounds(28, 186, 106, 25);
		desktopPane.add(lblComming);
		
		JLabel label = new JLabel(SendToServer.send("http://localhost:8000/passenger/travelkindsCount&id="+passenger.getId()+"&kind=comming"));
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 30));
		label.setBounds(170, 182, 250, 25);
		desktopPane.add(label);
		
		background.add(desktopPane);
		background.add(lblUsername);
		background.add(lblTravels);
		background.add(lblMoney);
		background.add(desktopPane_1);
		background.add(btnSeeAll);
		background.add(username);
		background.add(btnLogOut);
		background.add(btnCreateNewTravel);
		
		
		btnCreateNewTravel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Travel travel=new Travel(passenger);
				CreateTravelView.main(new String[] {},frame,travel);
				
			}
		});
		
		
		
		// EVENTS :
		btnSeeAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				TravelsView.main(new String[] {}, frame, passenger);
				
			}
		});

		btnAddMoney.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String add;
				int final_money;
				add = JOptionPane.showInputDialog(frame, "enter money : ", "add money", JOptionPane.INFORMATION_MESSAGE);
				//TRY TO KNOW USER INPUT IS NUMBERIC OR NOT :
				try {
					Integer.parseInt(add);
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, "ENTER NUMBER JUST!", "NOT NUMBERIC!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//LET KNOW USER INPUT IS OVER THAN ZERO? :
				if (Integer.parseInt(add) <= 0) {
					JOptionPane.showMessageDialog(frame, "can add lower than 0 !", "add error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				final_money = Integer.parseInt(add) + Integer.parseInt(money.getText());
				SendToServer.send("http://localhost:8000/passenger/addMoney&id="+passenger.getId()+"&money="+final_money);
				money.setText(final_money+"");
			}
		});
		
		btnLogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				LoginView.main(new String[] {});
			}
		});
	}
}
