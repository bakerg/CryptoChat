package io.github.bakerg;

import io.github.bakerg.packets.PacketCreateAccount;
import io.github.bakerg.packets.PacketCreateAccountResponse;
import io.github.bakerg.packets.PacketEncrypted;
import io.github.bakerg.packets.PacketLogin;
import io.github.bakerg.packets.PacketLoginResponse;

public class CCProtocol {
	public static Object handleInput(Object object){
		if(object instanceof PacketEncrypted){
			PacketEncrypted enpack = (PacketEncrypted)object;
			return (handleInput(Crypto.deserialize(Crypto.RSADecrypt(enpack.contents))));
		}
		else if(object instanceof PacketLogin){
			if(LoginHandler.checkLogin((PacketLogin)object)){
				return new PacketLoginResponse(true);
			}
			return new PacketLoginResponse(false);
		}
		else if(object instanceof PacketCreateAccount){
			if(LoginHandler.checkUsername((PacketCreateAccount)object)){
				System.out.println("uname available");
				if(LoginHandler.addUser((PacketCreateAccount)object)){
					System.out.println("account made");
					return new PacketCreateAccountResponse(true);
				}
			}
			return new PacketCreateAccountResponse(false);
		}
		return null;
	}
}
