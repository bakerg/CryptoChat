package io.github.bakerg;

import io.github.bakerg.packets.PacketCloseConnection;
import io.github.bakerg.packets.PacketLogin;
import io.github.bakerg.packets.PacketLoginResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Network {
	
	private static Socket socket = null;
	private static ObjectOutputStream out = null;
	private static ObjectInputStream in = null;
	public static void connect(String server){
		try {
			socket = new Socket(server, 1234);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static boolean login(String username, String password){
		try {
			out.writeObject(new PacketLogin(username, password));
			Object response;
			while(true){
				response = in.readObject();
				if(response != null){
					if(response instanceof PacketLoginResponse){
						PacketLoginResponse loginResponse = (PacketLoginResponse)response;
						if(loginResponse.loginSuccessful){
							System.out.println("Login Successful!");
							out.writeObject(new PacketCloseConnection());
							break;
						}else{
							System.out.println("Login Failed!");
							out.writeObject(new PacketCloseConnection());
							break;
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
