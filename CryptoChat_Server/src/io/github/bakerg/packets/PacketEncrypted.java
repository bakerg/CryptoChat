package io.github.bakerg.packets;

import java.io.Serializable;

public class PacketEncrypted implements Serializable{
	private static final long serialVersionUID = -1738352844256475034L;
	public byte[] contents;
	public boolean RSA;
	public PacketEncrypted(byte[] contents, boolean RSA){
		this.contents = contents;
		this.RSA = RSA;
	}
}
