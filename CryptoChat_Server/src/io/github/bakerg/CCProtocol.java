package io.github.bakerg;

import io.github.bakerg.packets.PacketCreateAccount;
import io.github.bakerg.packets.PacketCreateAccountResponse;
import io.github.bakerg.packets.PacketLogin;
import io.github.bakerg.packets.PacketLoginResponse;

public class CCProtocol {
	public static Object handleInput(Object object){
		if(object instanceof PacketLogin){
			if(LoginHandler.checkLogin((PacketLogin)object)){
				return new PacketLoginResponse(true);
			}
			return new PacketLoginResponse(false);
		}
		else if(object instanceof PacketCreateAccount){
			if(LoginHandler.checkUsername((PacketCreateAccount)object)){
				if(LoginHandler.addUser((PacketCreateAccount)object)){
					return new PacketCreateAccountResponse(true);
				}
			}
			return new PacketLoginResponse(false);
		}
		return null;
	}
}
