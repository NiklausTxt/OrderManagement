package com.orderManagement.command.impl;

import com.orderManagement.command.SystemCommand;
import com.orderManagement.util.Console;

public class ExitCommand extends SystemCommand{

	@Override
	public void excute() {
		Console.println("��ӭ�´�ʹ�ã��ټ�������");
		System.exit(0);	
	}

	

}
