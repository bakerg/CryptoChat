package io.github.bakerg;

import io.github.bakerg.packets.PacketCreateAccount;
import io.github.bakerg.packets.PacketLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginHandler {
	 Connection connection = null;
	 Statement statement = null;
	 
	 public LoginHandler(){
		 try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost/im", "default", "");
				statement = connection.createStatement(); 
			} catch (SQLException e) {
				System.out.println("Connection to sql server failed!");
				e.printStackTrace();
			}
	 }
	
	public  String checkLogin(PacketLogin login){
		if(isValid(login.username)){
			ResultSet result;
			try {
				result = statement.executeQuery("SELECT password FROM accounts WHERE username = '"+login.username+"';");
				result.first();
				if(login.passwordHash.equals(result.getString(1))){
					String identifier = Crypto.createIdentifier();
					statement.execute("UPDATE accounts SET identifier = '"+identifier+"' WHERE username = '"+login.username+"'; ");
					System.out.println("User '"+login.username+"' logged in!");
					return identifier;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "fail";
	}
	
	public  boolean checkUsername(PacketCreateAccount login){
		if(isValid(login.username)){
			ResultSet result;
			try {
				result = statement.executeQuery("SELECT * FROM accounts WHERE username = '"+login.username+"';");
				if(result.next() == false){
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Account creation failed for username '"+login.username+"'");
		return false;
	}
	
	public  boolean addUser(PacketCreateAccount login){
		try {
			statement.execute("INSERT INTO accounts(username, password) VALUES ('"+login.username+"','"+login.passwordHash+"');");
			System.out.println("Created account for user "+login.username);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public  boolean isValid(String username){
		if(!username.contains(";")){
			return true;
		}
		return false;
	}
	
}
