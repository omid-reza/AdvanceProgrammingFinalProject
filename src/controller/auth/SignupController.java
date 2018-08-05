package controller.auth;

import controller.driver.DriverController;
import controller.passenger.PassengerController;

public class SignupController {
	public static String signup(String username,String password,String kind) {
		if( ! uniqueCheck(username)) {
			return "0";
		}
		if (kind.equals("passenger")) {
			PassengerController.signup(username,password);
		}else { // kind.equals("driver")
			DriverController.signup(username,password);
		}
		return "1";
	}

	private static boolean uniqueCheck(String username) {
		if((PassengerController.uniqueCheck(username))&&(DriverController.uniqueCheck(username))) {
			return true;
		}
		return false;
		
	}
}
