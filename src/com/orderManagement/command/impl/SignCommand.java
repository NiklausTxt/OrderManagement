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
		Console.println("===========================欢迎来到注册界面===========================");
		int clientType = Console.askUserInputint("cmd> 请输入用户类型编号（1.个人用户	2.集体用户）：");
		while (true) {
			if (clientType == 1 || clientType == 2) {
				break;
			}
			clientType = Console.askUserInputint("cmd> 请输入用户类型编号（1.个人用户	2.集体用户）：");
		}
		
		String username = Console.askUserInput("cmd> 请输入用户名：");
		while (dao.isUsernameExists(username)) {
			Console.println("cmd> 你输入的用户名已存在");
			username = Console.askUserInput("cmd> 请输入用户名：");
		}
		if(clientType == 1){			
			nickname = Console.askUserInput("cmd> 请输入昵称：");
		}

		String password = null;
		while (true) {
			password = Console.askUserInput("cmd> 请输入密码：");
			try {
				validPassword(password);
				break;
			} catch (Exception e) {
				Console.println("cmd> 密码错误：" + e.getMessage());
				continue;
			}
		}

		String email = null;
		while (true) {
			email = Console.askUserInput("cmd> 请输入邮箱：");
			try {
				validEmail(email);
				break;
			} catch (Exception e) {
				Console.println(e.getMessage());
				continue;
			}
		}
		if(clientType==1){
			phone = Console.askUserInput("cmd> 请输入电话：");
		}

		if (clientType == 1) {
			client = new Client(nickname,phone,clientType);
			user = new User(username,password,email,client);
			
			dao.sign(user);

		} else if (clientType == 2) {
			int choose = Console.askUserInputint("cmd> 请选择（1.选择已有的集体客户	2.新建集体客户）：");
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
						int selectClient = Console.askUserInputint("cmd> 请选择的ID：");
						for (Client s : list) {
							if (s.getId() == selectClient) {
								client = s;
							}
						}
						user = new User(username, password, email, selectClient, client);
					}else{
						Console.println("不存在已有的集体客户，将新建集体用户");
						choose=2;
					}
					
				}else{
					Console.println("不存在已有的集体客户，将新建集体用户");
					choose=2;
				}
				
			}
			if(choose==2){
				nickname = Console.askUserInput("cmd> 请输入昵称：");
				phone = Console.askUserInput("cmd> 请输入电话：");
				int type=2;
				client = new Client(nickname, phone, clientType);
				user = new User(username, password, email, client);
			}
//			client = new Client(nickname,phone,clientType);
			
//			 player = new Player(username, password, email, phone, nickname,selectClient,clientType);
			 dao.sign(user);
			 Console.println("用户创建成功");
		}

	}

	private void validEmail(String email) throws Exception {
		Pattern pattern = Pattern.compile("\\p{Alpha}\\w{2,15}[@][a-z0-9]{2,}[.]\\p{Lower}{2,}");
		if (!pattern.matcher(email).find()) {
			throw new Exception("邮箱格式不正确");
		}
	}

	private void validPassword(String password) throws Exception {
		// 长度大于6
		Pattern lenPattern = Pattern.compile("[0-9a-zA-Z]{6,}");
		if (!lenPattern.matcher(password).find()) {
			throw new Exception("密码长度至少为6位");
		}

		// 至少包含一位数字
		Pattern numPattern = Pattern.compile("[0-9]");
		if (!numPattern.matcher(password).find()) {
			throw new Exception("密码至少需要一位数字");
		}

		// 至少包含一位字母
		Pattern wordPattern = Pattern.compile("[a-zA-z]");
		if (!wordPattern.matcher(password).find()) {
			throw new Exception("密码至少需要一位字母");
		}
	}
}
