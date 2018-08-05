package controller.driver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import controller.database.DB;

public class DriverController {
	public static int travelkindsCount(int driver_id,String kind){
		Statement statement;
		int count=0;
		try {
			statement = DB.connect();
			ResultSet result= statement.executeQuery("SELECT id FROM travels WHERE `driver_id`='"+driver_id+"' AND `status`='"+kind+"'");
			while(result.next()){
				count = count + 1;
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		return count;
	}
	
	public static boolean login(String username,String password){
		Statement statement;
		try {
			statement = DB.connect();
			ResultSet result= statement.executeQuery("SELECT id FROM drivers WHERE `username`='"+username+"' AND `PASSWORD`='"+password+"'");
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
			boolean result = statement.execute("INSERT INTO drivers (`username`,`password`)VALUES('"+username+"','"+password+"')");
			return ! result;
		} catch (ClassNotFoundException | SQLException e) {
			//No THING !!
		}
		return false;
			
	}

	public static boolean uniqueCheck(String username) {
		Statement statement;
		try {
			statement = DB.connect();
			ResultSet result= statement.executeQuery("SELECT id FROM drivers WHERE `username`='"+username+"'");
			while(result.next()) {
				return false;
			}
			return true;
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static int getByUsername(String userName) {
		Statement statement;
		try {
			statement = DB.connect();
			ResultSet result= statement.executeQuery("SELECT id FROM drivers WHERE `username`='"+userName+"'");
			if (result.next()) {
				return result.getInt("id");
			}
			
		}catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		return 0;
	}

//	public static ResultSet getById(int id) {
//		 Statement statement;
//		try {
//			statement = DB.connect();
//			ResultSet result= statement.executeQuery("SELECT * FROM drivers WHERE id="+id);
//			if (result.next()) {
//				return result;
//			}
//		}catch (ClassNotFoundException | SQLException e1) {
//			e1.printStackTrace();
//		}
//		return null;
//	}
}
