package controller.passenger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.Passenger;
import classes.User;
import controller.database.DB;

public class PassengerController{
	
	public static int travelkindsCount(int passenger_id,String kind){
		Statement statement;
		int count=0;
		try {
			statement = DB.connect();
			ResultSet result= statement.executeQuery("SELECT id FROM travels WHERE `passenger_id`='"+passenger_id+"' AND `status`='"+kind+"'");
			while(result.next()){
				count = count + 1;
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		return count;
	}

	
	public static boolean login(String username,String password) {
		Statement statement;
		try {
			statement = DB.connect();
			ResultSet result= statement.executeQuery("SELECT id FROM passengers WHERE `username`='"+username+"' AND `PASSWORD`='"+password+"'");
			while(result.next()) {
				return true;
			}
			return false;
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean signup(String username,String password) {
		Statement statement;
		try {
			statement = DB.connect();
			boolean result = statement.execute("INSERT INTO passengers (`username`,`password`)VALUES('"+username+"','"+password+"')");
			return ! result;
		} catch (ClassNotFoundException | SQLException e) {
			//No Thing!!!
		}
		return false;
	}
	
	public static boolean uniqueCheck(String username) {
		Statement statement;
		try {
			statement = DB.connect();
			ResultSet result= statement.executeQuery("SELECT id FROM passengers WHERE `username`='"+username+"'");
			if (result.next()) {
				return false;
			}
			return true;
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	
	public static int getIdByUsername(String userName) {
		Statement statement;
		try {
			statement = DB.connect();
			ResultSet result= statement.executeQuery("SELECT id FROM passengers WHERE `username`='"+userName+"'");
			if (result.next()) {
				return result.getInt("id");	
			}
			
		}catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		return -1;
	}


	public static String setpoint(int travel_id, int point) {
		Statement statement;
		try {
			statement = DB.connect();
			statement.execute("UPDATE travels SET `point`="+point+" WHERE id="+travel_id+";");
		}catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		return "1";
	}

}
