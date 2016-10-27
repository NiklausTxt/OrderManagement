package com.orderManagement.command.impl;

import java.util.List;

import com.orderManagement.Dao.DatabaseDao;
import com.orderManagement.command.PlayerCommand;
import com.orderManagement.entity.Order;
import com.orderManagement.entity.OrderItem;
import com.orderManagement.entity.User;
import com.orderManagement.util.Console;

public class payCommand extends PlayerCommand {

	public payCommand(User user) {
		super(user);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void excute() {
		DatabaseDao dao = new DatabaseDao();
		List<Order> orders = dao.getOrderList2(user);
		Console.println("�������¶�����Ҫ֧��");
		Order sOrder=null;
		for (Order order : orders) {
			Console.println(order.toString());
			double totalPrcice = 0.0;
			for (OrderItem o : order.getItems()) {
				totalPrcice += o.getAmount()*o.getProduct().getPrice();
				Console.println(o.toString());
			}
			order.setTotalPrice(totalPrcice);
		}
		int code = Console.askUserInputint("cmd> ������֧��������ţ�");
		for(Order order:orders){
			if(order.getId()==code){
				sOrder=order;
			}
		}
		dao.pay(user, code, sOrder);
		
	}

}
