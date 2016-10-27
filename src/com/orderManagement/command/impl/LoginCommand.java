package com.orderManagement.command.impl;

import com.orderManagement.Dao.DatabaseDao;
import com.orderManagement.app.TestMain;
import com.orderManagement.command.SystemCommand;
import com.orderManagement.entity.Player;
import com.orderManagement.entity.User;
import com.orderManagement.util.Console;

public class LoginCommand extends SystemCommand{
	@Override
	public void excute() {
		DatabaseDao dao = new DatabaseDao();
		Console.println("=====================欢迎来到用户登录界面=====================");
		
		String username = Console.askUserInput("请输入用户名：");
		while(!dao.isUsernameExists(username)){
			Console.println("用户名不存在");
			username = Console.askUserInput("请输入用户名：");
		}
		String password = Console.askUserInput("请输入密码：");
		User user = dao.login(username, password);
		if(user==null || user.getId()==-1){
			Console.println("登录失败，密码错误");
			return ;
		}
		if(user.getClient().getClientType()==1){
			Console.println("欢迎个人客户"+user.getUsername()+",登录成功！");
		}else if(user.getClient().getClientType()==2){
			Console.println("欢迎集体用户"+user.getUsername()+",登录成功！");
		}else{
			Console.println("欢迎");
		}
		TestMain.getInstance().setUser(user);
	}

	
}
