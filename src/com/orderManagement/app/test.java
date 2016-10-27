package com.orderManagement.app;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.orderManagement.Dao.DatabaseDao;
import com.orderManagement.command.Command;
import com.orderManagement.command.impl.CreateOrder;
import com.orderManagement.entity.Client;
import com.orderManagement.entity.Product;
import com.orderManagement.entity.User;
import com.orderManagement.util.Console;

public class test {

	public static void main(String[] args) {
		 DatabaseDao dao = new DatabaseDao();
		 Client client = new Client(2, "wang", "123343254", 1, 1);
		 User user = new User(1, "wang", "123qqq", "dfjidvj@fjie.cidj",
		 client);
		 Command c=new CreateOrder(user);
		 c.excute();

//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		String createTime = df.format(new Date());
//		Console.println(createTime);

	}
}
