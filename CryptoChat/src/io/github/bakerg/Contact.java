package io.github.bakerg;

import java.io.Serializable;
import java.sql.Date;

public class Contact implements Serializable{
	private static final long serialVersionUID = 6048428363021857311L;
	public String username;
	public byte[] AESKey, iv;
	public Date lastAESUpdate;
	public Contact(String username, byte[] AESKey, byte[] iv, Date lastAESUpdate){
		this.username = username;
		this.AESKey = AESKey;
		this.iv = iv;
		this.lastAESUpdate = lastAESUpdate;
	}
}
