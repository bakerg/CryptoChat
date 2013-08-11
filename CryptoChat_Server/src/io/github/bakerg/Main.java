package io.github.bakerg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.mariadb.jdbc.Driver;

public class Main {

	public static void main(String[] args) {
		LoginHandler.connectToDb();
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(1234);
		} catch (IOException e) {
			System.out.println("Could not bind to port 1234");
			e.printStackTrace();
		}
		
		Socket clientSocket = null;
		while(true){
			try {
				System.out.println("Here");
				clientSocket = serverSocket.accept();
				new ServerThread(clientSocket).run();
			} catch (IOException e) {
				System.out.println("Failed to accept on port 1234");
				e.printStackTrace();
			}
		}
	}

}
