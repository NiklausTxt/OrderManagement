package com.orderManagement.app;

import com.orderManagement.Dao.DatabaseDao;
import com.orderManagement.command.Command;
import com.orderManagement.command.CommandFactory;
import com.orderManagement.command.CommandFactory.CommandCode;
import com.orderManagement.command.impl.HelpCommand;
import com.orderManagement.entity.User;
import com.orderManagement.util.Console;

public class TestMain {
	
	public static void main(String[] args) {
		DatabaseDao dao = new DatabaseDao();
		dao.loadDriver();
		Command start = new HelpCommand();
		start.excute();
		while (true) {
			String cmd = Console.askUserInput("cmd> ");
			CommandCode cmdCode = null;
			try {
				cmdCode = CommandCode.valueOf(cmd.toUpperCase());
			} catch (Exception e) {
				Console.println("不合法输入...");
				continue;
			}
			
			CommandFactory fac = CommandFactory.getFactory(TestMain.getInstance().getUser());
			Command command = fac.buildCommand(cmdCode);
			command.excute();

		}
	}

	
	
	
	
	private static TestMain instance = null;

	private TestMain() {
	}

	public synchronized static TestMain getInstance() {
		if (instance == null) {
			instance = new TestMain();
		}
		return instance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}





	private User user;

	
}
