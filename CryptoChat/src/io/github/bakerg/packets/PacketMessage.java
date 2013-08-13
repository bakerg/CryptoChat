package io.github.bakerg.packets;

import java.io.Serializable;
import java.sql.Date;

public class PacketMessage implements Serializable{
	private static final long serialVersionUID = 7905241765420834248L;
	public String identifier, recipient;
	public byte[] message;
	public Date time;
	public PacketMessage(String identifier, Date time, byte[] message, String recipient){
		this.message = message;
		this.time = time;
		this.identifier = identifier;
		this.recipient = recipient;
	}
}
