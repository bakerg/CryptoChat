package io.github.bakerg.packets;

import java.io.Serializable;
import java.sql.Date;

public class PacketMessage implements Serializable{
	private static final long serialVersionUID = 7905241765420834248L;
	public String username, recipient;
	public byte[] message;
	public Date time;
	public PacketMessage(String username, Date time, byte[] message, String recipient){
		this.message = message;
		this.time = time;
		this.username = username;
		this.recipient = recipient;
	}
}
