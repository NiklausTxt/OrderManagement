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
		Console.println("=====================��ӭ������ֵ����=====================");
		int number = Console.askUserInputint("cmd> �������ֵ�Ľ�");
		dao.recharge(user, number);
		Console.println("�ɹ���ֵ��");
	}

}
