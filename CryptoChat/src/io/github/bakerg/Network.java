package io.github.bakerg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Network {
	
	private static Socket socket = null;
	private static PrintWriter out = null;
	private static BufferedReader in = null;
	public static void connect(String server){
		try {
			socket = new Socket(server, 1234);
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static boolean login(String username, String password){
		out.write("1,"+username+","+password);
		String fromServer;
		try {
			while ((fromServer = in.readLine()) != null) {
			    if(fromServer.contains("Successful")){
			    	return true;
			    }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
