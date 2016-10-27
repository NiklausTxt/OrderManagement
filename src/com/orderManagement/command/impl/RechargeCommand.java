package com.orderManagement.command.impl;

import com.orderManagement.Dao.DatabaseDao;
import com.orderManagement.command.PlayerCommand;
import com.orderManagement.entity.User;
import com.orderManagement.util.Console;

public class RechargeCommand extends PlayerCommand{

	public RechargeCommand(User user) {
		super(user);
	}

	@Override
	public void excute() {
		DatabaseDao dao = new DatabaseDao();
		Console.println("=====================欢迎来到充值界面=====================");
		int number = Console.askUserInputint("cmd> 请输入充值的金额：");
		dao.recharge(user, number);
		Console.println("成功充值！");
	}

}
