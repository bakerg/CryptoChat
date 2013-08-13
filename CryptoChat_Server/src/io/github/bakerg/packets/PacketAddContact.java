package io.github.bakerg.packets;

import java.io.Serializable;

public class PacketAddContact implements Serializable{
	private static final long serialVersionUID = -1484799466221454477L;
	public String username; 
	public PacketRSAPublic key;
	public PacketAddContact(String username, PacketRSAPublic key){
		this.username = username;
		this.key = key;
	}
}
