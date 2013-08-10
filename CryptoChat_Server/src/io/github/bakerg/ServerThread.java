package io.github.bakerg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable{
	private Socket socket;
	public ServerThread(Socket clientSocket){
		this.socket = clientSocket;
	}
	@Override
	public void run() {
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			out = new PrintWriter(this.socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		} catch (IOException e) {
			System.out.println("Failed to create IO streams!");
			e.printStackTrace();
		}
	}

}
