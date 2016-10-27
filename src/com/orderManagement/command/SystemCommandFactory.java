package com.orderManagement.command;

import java.util.HashMap;
import java.util.Map;

import com.orderManagement.command.impl.ExitCommand;
import com.orderManagement.command.impl.HelpCommand;
import com.orderManagement.command.impl.InvalidCommand;
import com.orderManagement.command.impl.LoginCommand;
import com.orderManagement.command.impl.SignCommand;

public class SystemCommandFactory extends CommandFactory{
	
	private static Map<CommandCode, Class<? extends Command>> commandMap = new HashMap<>();
	
	static {
		commandMap.put(CommandCode.SIGN, SignCommand.class);
		commandMap.put(CommandCode.LOGIN, LoginCommand.class);
		commandMap.put(CommandCode.HELP, HelpCommand.class);
		commandMap.put(CommandCode.EXIT, ExitCommand.class);
	}
	
	public Command buildCommand(CommandCode identifier){
		Class<? extends Command> cmdClass = commandMap.get(identifier);
		if(cmdClass != null){
			try {
				return cmdClass.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return new InvalidCommand();
	}
}
