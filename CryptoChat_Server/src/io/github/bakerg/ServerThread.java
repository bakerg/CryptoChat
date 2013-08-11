package io.github.bakerg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread implements Runnable{
	private Socket socket;
	public ServerThread(Socket clientSocket){
		this.socket = clientSocket;
	}
	@Override
	public void run() {
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		System.out.println("New thread started!");
		try {
			out = new ObjectOutputStream(this.socket.getOutputStream());
			in = new ObjectInputStream(this.socket.getInputStream());
			while(true){
				
			}
		} catch (IOException e) {
			System.out.println("Failed to create IO streams!");
			e.printStackTrace();
		}
		
	}

}
