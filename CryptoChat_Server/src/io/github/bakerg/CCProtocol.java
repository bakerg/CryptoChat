package io.github.bakerg;

public class CCProtocol {
	public static String handleInput(String input){
		if(input == null){
			return "0";
		}
		else if(input.startsWith("1")){
			if(LoginHandler.checkLogin(input)){
				return "Login Successful!";
			}
			return "Login Failed";
		}
		else if(input.startsWith("2")){
			if(LoginHandler.checkUsername(input)){
				if(LoginHandler.addUser(input)){
					return "Successfully Created account";
				}
				return "Account creation failed!";
			}
			return "Username taken!";
		}
		return "0";
	}
}
