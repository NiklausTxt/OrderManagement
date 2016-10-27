package com.orderManagement.command.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.orderManagement.Dao.DatabaseDao;
import com.orderManagement.command.PlayerCommand;
import com.orderManagement.entity.Order;
import com.orderManagement.entity.OrderItem;
import com.orderManagement.entity.Payment;
import com.orderManagement.entity.Product;
import com.orderManagement.entity.User;
import com.orderManagement.util.Console;

public class CreateOrder extends PlayerCommand{

	
	public CreateOrder(User user) {
		super(user);
		// TODO Auto-generated constructor stub
	}

	
	public static List<Product> lists;
	@Override
	public void excute() {
		DatabaseDao dao = new DatabaseDao();
		Console.println("cmd> 请从下列商品中选择你要购买的商品(输入-1结束):");
		lists =dao.getProductList();;
		OrderItem orderItem;
		Product product = null;
		Payment payment = new Payment();
		List<OrderItem> Items = new ArrayList<>();
		Order order = null;
		int i=1;
		for(Product p: lists){
			Console.println(p.toString());
		}
		while(true){
			String code = Console.askUserInput("cmd> 请输入商品编号:");
			if(code.equals("-1")){
				break;
			}
			if(isProductExists(code)!=null){
				product = isProductExists(code);
				int amount = Console.askUserInputint("cmd> 请输入商品数量");
				orderItem = new OrderItem(i, amount, null,user.getClient().getId(), product);
				i++;
				Items.add(orderItem);
			}else{
				Console.println("cmd> 请输入的商品编号不存在");
				continue;
			}
			
		}
		while(true){
			int payMethod = Console.askUserInputint("请输入支付方式(1.现金	2.支付宝)：");
			if(payMethod==1){
				payment.setpMethod("现金");
				break;
			}else if(payMethod==2){
				payment.setpMethod("支付宝");
				break;
			}else{
				Console.println("输入不合法");
				continue;
			}
			
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime =df.format(new Date());
		Console.println(createTime);
		
		int userId = user.getClient().getId();
		double totalPrice = 0;
		order = new Order(createTime, userId, payment, Items);
		Console.println(order.toString());
		for(OrderItem o:Items){
			totalPrice +=o.getAmount()*o.getProduct().getPrice();
			Console.println(o.toString());
		}
		Console.println("总价为："+totalPrice);
		dao.createOrder(order);
	}

	private Product isProductExists(String id){
		Product product=null;
		for(Product p : lists){
			if(p.getCode().equals(id)){
				product=p;
			}
		}
		return product;
	}
}
