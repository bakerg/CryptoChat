package io.github.bakerg.packets;

import java.io.Serializable;

public class PacketLogin implements Serializable{
	private static final long serialVersionUID = -5849519141227484158L;
	public String username, passwordHash;
	public PacketLogin(String username, String passwordHash){
		this.username = username;
		this.passwordHash = passwordHash;
	}
}
