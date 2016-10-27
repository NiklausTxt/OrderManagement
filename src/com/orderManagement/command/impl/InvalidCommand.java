package com.orderManagement.command.impl;

import com.orderManagement.command.Command;
import com.orderManagement.util.Console;

public class InvalidCommand implements Command {

	@Override
	public void excute() {
		Console.println("输入不合法...");
	}

}
