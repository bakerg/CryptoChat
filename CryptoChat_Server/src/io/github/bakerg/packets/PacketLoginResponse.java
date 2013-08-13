package io.github.bakerg.packets;

import java.io.Serializable;

public class PacketLoginResponse implements Serializable{
	private static final long serialVersionUID = 724043919519375872L;
	public boolean loginSuccessful;
	public String identifier;
	public PacketLoginResponse(boolean success, String identifier){
		this.identifier = identifier;
		this.loginSuccessful = success;
	}
}
