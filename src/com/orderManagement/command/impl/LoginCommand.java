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
		Console.println("=====================��ӭ�����û���¼����=====================");
		
		String username = Console.askUserInput("�������û�����");
		while(!dao.isUsernameExists(username)){
			Console.println("�û���������");
			username = Console.askUserInput("�������û�����");
		}
		String password = Console.askUserInput("���������룺");
		User user = dao.login(username, password);
		if(user==null || user.getId()==-1){
			Console.println("��¼ʧ�ܣ��������");
			return ;
		}
		if(user.getClient().getClientType()==1){
			Console.println("��ӭ���˿ͻ�"+user.getUsername()+",��¼�ɹ���");
		}else if(user.getClient().getClientType()==2){
			Console.println("��ӭ�����û�"+user.getUsername()+",��¼�ɹ���");
		}else{
			Console.println("��ӭ");
		}
		TestMain.getInstance().setUser(user);
	}

	
}
