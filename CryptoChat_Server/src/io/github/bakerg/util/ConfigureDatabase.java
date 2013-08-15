package io.github.bakerg.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConfigureDatabase {
	static Connection connection = null;
	static Statement statement = null;
	static String user = "default"; //SQL User - Needs permission to create databases and tables!
	static String password = ""; //Password for SQL User
	static String serverURL = "jdbc:mysql://localhost"; //URL of the server
	
	public static void connectToDb(){
		try {
			connection = DriverManager.getConnection(serverURL, user, password);
			statement = connection.createStatement(); 
		} catch (SQLException e) {
			System.out.println("Connection to sql server failed!");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		connectToDb();
		try {
			statement.execute("CREATE DB im;");
			statement.execute("CREATE TABLE accounts(username varchar(64), password varchar(64), identifier varchar(32));");
			statement.execute("CREATE TABLE messages(user1 varchar(64), user2 varchar(64), messages longblob);");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Database successfully configured");
	}
	
}
