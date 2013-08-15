package io.github.bakerg;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(1234);
		} catch (IOException e) {
			System.out.println("Could not bind to port 1234");
			e.printStackTrace();
		}
		
		Socket clientSocket = null;
		System.out.println("SERVER STARTED SUCCESSFULLY");
		while(true){
			try {
				clientSocket = serverSocket.accept();
				System.out.println("Connection from "+clientSocket.getInetAddress());
				new ServerThread(clientSocket).run();
			} catch (IOException e) {
				System.out.println("Failed to accept on port 1234");
				e.printStackTrace();
			}
		}
	}

}
