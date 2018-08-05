package controller.travel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import controller.database.DB;

public class TravelController {
	
	public static ResultSet getPassengerTravels(int passenger_id) {
		Statement statement;
		try {
			statement = DB.connect();
			ResultSet result = statement.executeQuery("SELECT * FROM travels WHERE passenger_id=" + passenger_id);
			return result;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ResultSet getDriverTravels(int driver_id) {
		Statement statement;
		try {
			statement = DB.connect();
			ResultSet result = statement.executeQuery("SELECT * FROM travels WHERE driver_id=" + driver_id);
			return result;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
				
	}
		
	public static String cancel(int travel_id) {
		Statement statement;
		try {
			statement = DB.connect();
			statement.execute("UPDATE travels SET status='canceled' WHERE id="+travel_id);
		}catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		return "1";
	}
	public static String reate(String pasenger_id,String cost,String From_place_latitude,String From_place_longitude,String To_place_lltitude,String To_place_longitude) {
		Statement statement;
		try {
			statement = DB.connect();
			
			statement.execute("INSERT INTO travels "
									+ "(`passenger_id`,"
									+ "`from_place_lltitude`,"
									+ "`from_place_longitude`,"
									+ "`to_place_lltitude`,"
									+ "`to_place_longitude`,"
									+ "`cost`"
									+ ")VALUES("
									+pasenger_id+","
									+From_place_latitude+","
									+From_place_longitude+","
									+To_place_lltitude+","
									+To_place_longitude+","
									+cost+")");
			return "1";
		}catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}	
		return "0";
	}
	public static ResultSet findPassengers() {
		Statement statement;
		ResultSet result;
		try {
			statement = DB.connect();
			result= statement.executeQuery("SELECT cost,id,from_place_lltitude,from_place_longitude,to_place_lltitude,to_place_longitude from travels WHERE `status`='wait';");
			return result;
		}catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	public static String AcceptTravelByDriver(int travel_id,int driver_id) {
		Statement statement;
		try {
			statement = DB.connect();
			statement.execute("UPDATE travels SET driver_id="+driver_id+",status='comming' WHERE id="+travel_id+";");
			return "1";
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return "0";
	}

	public static int setFinish(int travel_id) {
		Statement statement;
		try {
			statement = DB.connect();
			statement.execute("UPDATE travels SET status='finished' WHERE `id`='"+travel_id+"';");
			statement=null;
			statement = DB.connect();
			ResultSet res=statement.executeQuery("SELECT cost FROM travels WHERE `id`='"+travel_id+"';");
			if(res.next()) {
				return res.getInt("cost");
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return 0;
	}
}
