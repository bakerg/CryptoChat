package io.github.bakerg.packets;

import java.io.Serializable;

public class PacketAESIV implements Serializable{
	private static final long serialVersionUID = -4977794098587101907L;
	public byte[] iv;
	public PacketAESIV(byte[] iv){
		this.iv = iv;
	}
}
