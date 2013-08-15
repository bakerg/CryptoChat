package io.github.bakerg;

import java.util.Arrays;

import io.github.bakerg.packets.PacketAESIV;
import io.github.bakerg.packets.PacketAESKey;
import io.github.bakerg.packets.PacketCreateAccount;
import io.github.bakerg.packets.PacketCreateAccountResponse;
import io.github.bakerg.packets.PacketEncrypted;
import io.github.bakerg.packets.PacketLogin;
import io.github.bakerg.packets.PacketLoginResponse;

public class CCProtocol {
	private  byte[] iv, AESKey;
	
	LoginHandler loginHandler;
	public CCProtocol(){
		loginHandler = new LoginHandler();
	}
	
	public  Object handleInput(Object object){
		if(object instanceof PacketEncrypted){
			PacketEncrypted enpack = (PacketEncrypted)object;
			if(enpack.RSA){
				return (handleInput(Crypto.deserialize(Crypto.RSADecrypt(enpack.contents))));
			}else{
				return (handleInput(Crypto.deserialize(Crypto.AESDecrypt(enpack.contents, AESKey, iv))));
			}
			
		}
		if(object instanceof PacketAESKey){
			AESKey = ((PacketAESKey) object).key;
			System.out.println("AESKEY: "+Arrays.toString(AESKey));
			iv = Crypto.generateIV();
			System.out.println("IV: " +Arrays.toString(iv));
			return new PacketAESIV(iv);
		}
		else if(object instanceof PacketLogin){
			String identifier = loginHandler.checkLogin((PacketLogin)object);
			if(!identifier.equals("fail")){
				return new PacketEncrypted(Crypto.AESEncrypt(Crypto.serialize(new PacketLoginResponse(true, identifier)), AESKey), false);
			}
			return new PacketLoginResponse(false, null);
		}
		else if(object instanceof PacketCreateAccount){
			if(loginHandler.checkUsername((PacketCreateAccount)object)){
				if(loginHandler.addUser((PacketCreateAccount)object)){
					return new PacketCreateAccountResponse(true);
				}
			}
			return new PacketCreateAccountResponse(false);
		}
		return null;
	}
}
