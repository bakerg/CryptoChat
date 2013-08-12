package io.github.bakerg;

import io.github.bakerg.packets.PacketCloseConnection;

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
		Object request;
		try {
			out = new ObjectOutputStream(this.socket.getOutputStream());
			in = new ObjectInputStream(this.socket.getInputStream());
			while(true){
				try{
					request = in.readObject();
					if(request != null){
						if(request instanceof PacketCloseConnection){
							break;
						}
						out.writeObject(CCProtocol.handleInput(request));
					}
				}catch (java.io.EOFException e){}
			}
			out.close();
			in.close();
			System.out.println("Connection to "+socket.getInetAddress()+" closed");
		} catch (IOException e) {
			System.out.println("Failed to create IO streams!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}

}
