package com.orderManagement.command.impl;

import java.util.List;
import java.util.regex.Pattern;

import com.orderManagement.Dao.DatabaseDao;
import com.orderManagement.command.SystemCommand;
import com.orderManagement.entity.Client;
import com.orderManagement.entity.Player;
import com.orderManagement.entity.User;
import com.orderManagement.util.Console;

public class SignCommand extends SystemCommand {

	@Override
	public void excute() {
		DatabaseDao dao = new DatabaseDao();
		User user = null;
		Client client = null;
		String nickname=null;
		String phone = null;
		Console.println("===========================��ӭ����ע�����===========================");
		int clientType = Console.askUserInputint("cmd> �������û����ͱ�ţ�1.�����û�	2.�����û�����");
		while (true) {
			if (clientType == 1 || clientType == 2) {
				break;
			}
			clientType = Console.askUserInputint("cmd> �������û����ͱ�ţ�1.�����û�	2.�����û�����");
		}
		
		String username = Console.askUserInput("cmd> �������û�����");
		while (dao.isUsernameExists(username)) {
			Console.println("cmd> ��������û����Ѵ���");
			username = Console.askUserInput("cmd> �������û�����");
		}
		if(clientType == 1){			
			nickname = Console.askUserInput("cmd> �������ǳƣ�");
		}

		String password = null;
		while (true) {
			password = Console.askUserInput("cmd> ���������룺");
			try {
				validPassword(password);
				break;
			} catch (Exception e) {
				Console.println("cmd> �������" + e.getMessage());
				continue;
			}
		}

		String email = null;
		while (true) {
			email = Console.askUserInput("cmd> ���������䣺");
			try {
				validEmail(email);
				break;
			} catch (Exception e) {
				Console.println(e.getMessage());
				continue;
			}
		}
		if(clientType==1){
			phone = Console.askUserInput("cmd> ������绰��");
		}

		if (clientType == 1) {
			client = new Client(nickname,phone,clientType);
			user = new User(username,password,email,client);
			
			dao.sign(user);

		} else if (clientType == 2) {
			int choose = Console.askUserInputint("cmd> ��ѡ��1.ѡ�����еļ���ͻ�	2.�½�����ͻ�����");
			List<Client> list = dao.getClient();
			if(choose==1){
				if(list!=null){
					boolean flag = false;
					for (Client s : list) {
						if(s.getClientType()==2){
							Console.println(s.toString());		
							flag=true;
						}
					}
					if(flag){
						int selectClient = Console.askUserInputint("cmd> ��ѡ���ID��");
						for (Client s : list) {
							if (s.getId() == selectClient) {
								client = s;
							}
						}
						user = new User(username, password, email, selectClient, client);
					}else{
						Console.println("���������еļ���ͻ������½������û�");
						choose=2;
					}
					
				}else{
					Console.println("���������еļ���ͻ������½������û�");
					choose=2;
				}
				
			}
			if(choose==2){
				nickname = Console.askUserInput("cmd> �������ǳƣ�");
				phone = Console.askUserInput("cmd> ������绰��");
				int type=2;
				client = new Client(nickname, phone, clientType);
				user = new User(username, password, email, client);
			}
//			client = new Client(nickname,phone,clientType);
			
//			 player = new Player(username, password, email, phone, nickname,selectClient,clientType);
			 dao.sign(user);
			 Console.println("�û������ɹ�");
		}

	}

	private void validEmail(String email) throws Exception {
		Pattern pattern = Pattern.compile("\\p{Alpha}\\w{2,15}[@][a-z0-9]{2,}[.]\\p{Lower}{2,}");
		if (!pattern.matcher(email).find()) {
			throw new Exception("�����ʽ����ȷ");
		}
	}

	private void validPassword(String password) throws Exception {
		// ���ȴ���6
		Pattern lenPattern = Pattern.compile("[0-9a-zA-Z]{6,}");
		if (!lenPattern.matcher(password).find()) {
			throw new Exception("���볤������Ϊ6λ");
		}

		// ���ٰ���һλ����
		Pattern numPattern = Pattern.compile("[0-9]");
		if (!numPattern.matcher(password).find()) {
			throw new Exception("����������Ҫһλ����");
		}

		// ���ٰ���һλ��ĸ
		Pattern wordPattern = Pattern.compile("[a-zA-z]");
		if (!wordPattern.matcher(password).find()) {
			throw new Exception("����������Ҫһλ��ĸ");
		}
	}
}
