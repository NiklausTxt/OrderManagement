package com.orderManagement.command.impl;

import com.orderManagement.Dao.DatabaseDao;
import com.orderManagement.command.PlayerCommand;
import com.orderManagement.entity.User;
import com.orderManagement.util.Console;

public class AccountCommand extends PlayerCommand {

	public AccountCommand(User user) {
		super(user);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void excute() {
		DatabaseDao dao = new DatabaseDao();
		Console.print("ÄúµÄÕË»§Óà¶îÎª£º");
		double balance = dao.showBalance(user);
		Console.println(balance+"");
	}

}
