package com.orderManagement.command.impl;

import com.orderManagement.command.SystemCommand;
import com.orderManagement.util.Console;

public class ExitCommand extends SystemCommand{

	@Override
	public void excute() {
		Console.println("欢迎下次使用，再见！！！");
		System.exit(0);	
	}

	

}
