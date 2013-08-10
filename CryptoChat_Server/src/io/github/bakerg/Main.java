package io.github.bakerg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mariadb.jdbc.Driver;

public class Main {

	public static void main(String[] args) {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/im", "default", "");
			Statement statement = connection.createStatement(); 
			ResultSet result = statement.executeQuery("SELECT password FROM accounts WHERE username = 'bakerg';");
			result.first();
			System.out.println(result.getString(1));
		} catch (SQLException e) {
			System.out.println("Connection to sql server failed!");
			e.printStackTrace();
		}
		
	}

}
