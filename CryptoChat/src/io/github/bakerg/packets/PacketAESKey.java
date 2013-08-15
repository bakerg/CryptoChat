package io.github.bakerg.packets;

import java.io.Serializable;

public class PacketAESKey implements Serializable{
	private static final long serialVersionUID = 8952400062319816169L;
	public byte[] key;
	public PacketAESKey(byte[] key){
		this.key = key;
	}
}
