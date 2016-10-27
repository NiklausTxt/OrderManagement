package com.orderManagement.command.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.orderManagement.Dao.DatabaseDao;
import com.orderManagement.command.PlayerCommand;
import com.orderManagement.entity.Order;
import com.orderManagement.entity.OrderItem;
import com.orderManagement.entity.User;
import com.orderManagement.util.Console;

public class QueryCommand extends PlayerCommand {

	public QueryCommand(User user) {
		super(user);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void excute() {
		DatabaseDao dao = new DatabaseDao();
		List<Order> orders = dao.getOrderList(user);
		if(orders!=null){
			Console.println("订单列表如下：");
			for(Order order : orders){
				Console.println(order.toString());
				for(OrderItem o:order.getItems()){
					Console.println(o.toString());
				}
			}
		}else{
			Console.println("您还没有订单");
		}
	}
	
}
