package com.orderManagement.command;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.orderManagement.command.impl.AccountCommand;
import com.orderManagement.command.impl.CreateOrder;
import com.orderManagement.command.impl.QueryCommand;
import com.orderManagement.command.impl.RechargeCommand;
import com.orderManagement.command.impl.payCommand;
import com.orderManagement.entity.User;


public class PlayerCommandFactory extends SystemCommandFactory{

	private User user;
	public PlayerCommandFactory(User user) {
		this.user=user;
	}
	
	public static Map<CommandCode, Class<? extends Command>> commandMap = new HashMap<>();
	
	static {
		commandMap.put(CommandCode.ORDER, CreateOrder.class);
		commandMap.put(CommandCode.RECHARGE, RechargeCommand.class);
		commandMap.put(CommandCode.QUERY, QueryCommand.class);
		commandMap.put(CommandCode.PAY, payCommand.class);
		commandMap.put(CommandCode.ACCOUNT, AccountCommand.class);
	}
	
	@Override
	public Command buildCommand(CommandCode cmd) {
		Command command =null;
		
		Class<? extends Command> cmdClass = commandMap.get(cmd);
		if(cmdClass!=null){
			try {
				@SuppressWarnings("unchecked")
				Constructor<Command> constructor = (Constructor<Command>) cmdClass.getConstructor(User.class);
				
				command = constructor.newInstance(user);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		
		if(command==null){
			command = super.buildCommand(cmd);
		}
		return command;
	}

}
