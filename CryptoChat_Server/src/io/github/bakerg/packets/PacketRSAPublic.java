package io.github.bakerg.packets;

import java.io.Serializable;
import java.security.PublicKey;

public class PacketRSAPublic implements Serializable{
	private static final long serialVersionUID = 433915516007164672L;
	public PublicKey key;
	public PacketRSAPublic(PublicKey key){
		this.key = key;
	}
}
