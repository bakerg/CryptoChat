package io.github.bakerg.packets;

import java.io.Serializable;
import java.sql.Timestamp;

public class PacketMessage implements Serializable{
	private static final long serialVersionUID = 7905241765420834248L;
	public String username, recipient;
	public byte[] message;
	public Timestamp time;
	public PacketMessage(String username, Timestamp time, byte[] message, String recipient){
		this.message = message;
		this.time = time;
		this.username = username;
		this.recipient = recipient;
	}
}
