package io.github.bakerg;

import io.github.bakerg.packets.PacketCreateAccount;
import io.github.bakerg.packets.PacketLogin;

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
	public static boolean checkLogin(PacketLogin login){
		ResultSet result;
		try {
			result = statement.executeQuery("SELECT password FROM accounts WHERE username = '"+login.username+"';");
			result.first();
			System.out.println(result.getString(1));
			System.out.println("Result: "+result.getString(1)+" Hash: "+login.passwordHash.toString() +" Are they the same?: "+login.passwordHash.equalsIgnoreCase(result.getString(1)));
			if(login.passwordHash.contains(result.getString(1))){
				System.out.println(true);
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean checkUsername(PacketCreateAccount login){
		ResultSet result;
		try {
			result = statement.executeQuery("SELECT * FROM accounts WHERE username = '"+login.username+"';");
			if(result == null){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean addUser(PacketCreateAccount login){
		try {
			return statement.execute("INSERT INTO accounts(username, password) VALUES ('"+login.username+"','"+login.passwordHash+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
