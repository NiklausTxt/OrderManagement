package com.orderManagement.command.impl;

import com.orderManagement.command.SystemCommand;
import com.orderManagement.util.Console;

public class HelpCommand extends SystemCommand{
	@Override
	public void excute() {
		Console.println("欢迎来到此系统，请选择以下内容");
		Console.println("Login--用户登录");
		Console.println("Signup--用户注册");
		Console.println("exit--退出");
		Console.println("Help--显示帮助信息");
		Console.println("==========登录之后============");
		Console.println("order--下订单");
		Console.println("query--查询所有订单");
		Console.println("pay--支付订单");
		Console.println("recharge--账户充值");
		Console.println("account--显示账户余额");
	}

}
