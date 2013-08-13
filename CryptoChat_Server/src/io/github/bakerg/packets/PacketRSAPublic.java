package io.github.bakerg.packets;

import java.io.Serializable;
import java.security.spec.RSAPublicKeySpec;

public class PacketRSAPublic implements Serializable{
	private static final long serialVersionUID = 433915516007164672L;
	public RSAPublicKeySpec key;
	public PacketRSAPublic(RSAPublicKeySpec key){
		this.key = key;
	}
}
