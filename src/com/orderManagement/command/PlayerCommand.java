package com.orderManagement.command;

import com.orderManagement.entity.User;

public abstract class PlayerCommand implements Command{
	
	protected User user;
	
	public PlayerCommand(User user){
		this.user=user;
	}
}
