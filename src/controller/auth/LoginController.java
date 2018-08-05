package controller.auth;

import controller.driver.DriverController;
import controller.passenger.PassengerController;
import classes.User;

public class LoginController {
	public static String driverlogin(String username,String password){ 
		if (DriverController.login(username,password)) {
			return "1";
		}
		return "0";
	}
	public static String passengerLogin(String username,String password){ 
		if (PassengerController.login(username,password)) {
			return "1";
		}
		return "0";
	}
}
