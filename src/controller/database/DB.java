package controller.database;

import java.sql.*;

public class DB {
	private static Connection dbconnection;
	private static String database="snap";
	private static String username="YOUR_USERNAME";
	private static String password="YOUR_PASSWORD";
	public static Statement connect() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		dbconnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database,username,password);
		Statement statement = dbconnection.createStatement();
		return statement;
	}
}
