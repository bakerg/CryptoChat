package io.github.bakerg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginHandler {
	static Connection connection = null;
	static Statement statement = null;
	public static void connectToDb(){
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/im", "default", "");
			statement = connection.createStatement(); 
		} catch (SQLException e) {
			System.out.println("Connection to sql server failed!");
			e.printStackTrace();
		}
	}
	public static boolean checkLogin(String input){
		ResultSet result;
		String[] login = StringHandler.parseInput(input);
		try {
			result = statement.executeQuery("SELECT password FROM accounts WHERE username = '"+login[1]+"';");
			result.first();
			if(login[2].equals(result.getString(1))){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean checkUsername(String input){
		ResultSet result;
		String[] login = StringHandler.parseInput(input);
		try {
			result = statement.executeQuery("SELECT * FROM accounts WHERE username = '"+login[1]+"';");
			if(result == null){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean addUser(String input){
		ResultSet result;
		String[] login = StringHandler.parseInput(input);
		try {
			return statement.execute("INSERT INTO accounts(username, password) VALUES ('"+login[1]+"','"+login[2]+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
