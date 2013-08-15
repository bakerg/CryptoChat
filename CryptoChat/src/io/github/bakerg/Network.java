package io.github.bakerg;

import io.github.bakerg.packets.PacketAESIV;
import io.github.bakerg.packets.PacketAESKey;
import io.github.bakerg.packets.PacketCloseConnection;
import io.github.bakerg.packets.PacketCreateAccount;
import io.github.bakerg.packets.PacketCreateAccountResponse;
import io.github.bakerg.packets.PacketEncrypted;
import io.github.bakerg.packets.PacketLogin;
import io.github.bakerg.packets.PacketLoginResponse;
import io.github.bakerg.packets.PacketMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Arrays;

public class Network {
	
	private static Socket socket = null;
	private static ObjectOutputStream out = null;
	private static ObjectInputStream in = null;
	private static String serverAddress = "localhost";
	private static String identifier;
	private static byte[] iv, AESKey;
	public static void connect(){
		try {
			socket = new Socket(serverAddress, 1234);  //Create IO Streams
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AESKey = Crypto.AESKeyGen();
		System.out.println("Generated Key: "+Arrays.toString(AESKey));
		writeEncryptedPacket(new PacketAESKey(AESKey), true); //Transmit generated AES Key to server, RSA Encrypted
		try {
			Object response = in.readObject();
			if(response != null){
				if(response instanceof PacketAESIV){
					System.out.println("Received IV!: "+Arrays.toString(((PacketAESIV)response).iv));
					iv = ((PacketAESIV) response).iv;
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static boolean login(String username, String passwordHash){
		try {
			writeEncryptedPacket(new PacketLogin(username, passwordHash), false); //Send Login packet with AES encryption
			Object response;
			response = in.readObject();
			if(response != null){
				if(response instanceof PacketEncrypted){
					response = Crypto.deserialize(Crypto.AESDecrypt(((PacketEncrypted) response).contents, AESKey, iv));
				}
				if(response instanceof PacketLoginResponse){
					PacketLoginResponse loginResponse = (PacketLoginResponse)response;
					if(loginResponse.loginSuccessful){
						identifier = loginResponse.identifier;
						out.writeObject(new PacketCloseConnection());
						return true;
					}else{
						out.writeObject(new PacketCloseConnection());
						return false;
					}
				}
			}
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean createAccount(String username, String passwordHash) {
		try {
			writeEncryptedPacket(new PacketCreateAccount(username, passwordHash), false);
			Object response;
			response = in.readObject();
			if(response != null){
				if(response instanceof PacketCreateAccountResponse){
					PacketCreateAccountResponse accountResponse = (PacketCreateAccountResponse)response;
					if(accountResponse.creationSuccessful){
						System.out.println("Account Creation Successful!");
						out.writeObject(new PacketCloseConnection());
					}else{
						System.out.println("Account Creation Failed!");
						out.writeObject(new PacketCloseConnection());
					}
				}
			}
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean sendMessage(String message){
		writeEncryptedPacket(new PacketMessage(identifier, new Timestamp(new Date().getTime()), Crypto.AESEncrypt(Crypto.serialize(message), AESKey, iv), identifier), false);
		return false;
	}
	
	public static void setAddress(String address){
		serverAddress = address;
	}
	
	public static String getAddress(){
		return serverAddress;
	}
	
	public static void writeEncryptedPacket(Object object, boolean RSA){
		try {
			if(RSA){
				out.writeObject(new PacketEncrypted(Crypto.RSAEncrypt(Crypto.serialize(object)), true));
			}else {
				out.writeObject(new PacketEncrypted(Crypto.AESEncrypt(Crypto.serialize(object), AESKey, iv),false));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
