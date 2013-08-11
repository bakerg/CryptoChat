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
			if(!result.isBeforeFirst()){
				result.first();
				if(login.passwordHash.equals(result.getString(1))){
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean checkUsername(PacketCreateAccount login){
		ResultSet result;
		try {
			result = statement.executeQuery("SELECT * FROM accounts WHERE username = '"+login.username+"';");
			if(result.isBeforeFirst()){
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
