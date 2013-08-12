package io.github.bakerg.packets;

import java.io.Serializable;

public class PacketCreateAccount implements Serializable{
	private static final long serialVersionUID = 6200495373556789975L;
	public String username, passwordHash;
	public PacketCreateAccount(String username, String passwordHash){
		this.username = username;
		this.passwordHash = passwordHash;
	}
}
