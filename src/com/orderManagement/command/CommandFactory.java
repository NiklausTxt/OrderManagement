package com.orderManagement.command;

import com.orderManagement.entity.Player;
import com.orderManagement.entity.User;

public abstract class CommandFactory {
	
	public static enum CommandCode{
		//system
		EXIT,
		HELP,
		LOGIN,
		SIGN,
		//player
		ORDER,
		RECHARGE,
		QUERY,
		PAY,
		ACCOUNT
	}
	
	public static CommandFactory getFactory(User user){
		if(user == null || user.getId()==-1){
			return new SystemCommandFactory();
		}
		return new PlayerCommandFactory(user);
	}
	
	public abstract Command buildCommand(CommandCode cmd);
}
