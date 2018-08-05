package controller.driver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controller.database.DB;

public class SalarayController {
	public static int getSalary(int driver_id) {
		Statement statement;
		try {
			statement = DB.connect();
			ResultSet result= statement.executeQuery("SELECT salary FROM drivers WHERE `id`='"+driver_id+"'");
			if (result.next()) {
				return result.getInt("salary");	
			}
		}catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		return 0;
	}
	
	public static String addSalary(int driver_id,int cost) {
		Statement statement;
		try {
			statement = DB.connect();
			int money=0;
			ResultSet res=statement.executeQuery("SELECT salary FROM drivers WHERE `id`='"+driver_id+"';");
			if (res.next()) {
				money=res.getInt("salary");
			}
			money=money+cost;
			statement=null;
			statement = DB.connect();
			statement.execute("UPDATE drivers SET salary="+money+" WHERE `id`='"+driver_id+"';");
			return "1";
		}catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		return "0";
	}
}
