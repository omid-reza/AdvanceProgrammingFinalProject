package controller.passenger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controller.database.DB;

public class MoneyController {
	
	public static int getmoney(int passenger_id) {
		Statement statement;
		try {
			statement = DB.connect();
			ResultSet result= statement.executeQuery("SELECT money FROM passengers WHERE `id`='"+passenger_id+"'");
			if (result.next()) {
				return result.getInt("money");	
			}
		}catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		return 0;
	}
	
	public static String addMoney(int passenger_id,int money) {
		Statement statement;
		try {
			statement = DB.connect();
			statement.execute("UPDATE passengers SET money="+money+" WHERE `id`="+passenger_id);
		}catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		return "1";
	}
}
