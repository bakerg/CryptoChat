package io.github.bakerg.packets;

import java.io.Serializable;

public class PacketCreateAccountResponse implements Serializable{
	private static final long serialVersionUID = 1525631574743843821L;
	public boolean creationSuccessful;
	public PacketCreateAccountResponse(boolean success){
		this.creationSuccessful = success;
	}
}
