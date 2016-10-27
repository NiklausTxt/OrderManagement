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
		Console.println("cmd> ���������Ʒ��ѡ����Ҫ�������Ʒ(����-1����):");
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
			String code = Console.askUserInput("cmd> ��������Ʒ���:");
			if(code.equals("-1")){
				break;
			}
			if(isProductExists(code)!=null){
				product = isProductExists(code);
				int amount = Console.askUserInputint("cmd> ��������Ʒ����");
				orderItem = new OrderItem(i, amount, null,user.getClient().getId(), product);
				i++;
				Items.add(orderItem);
			}else{
				Console.println("cmd> ���������Ʒ��Ų�����");
				continue;
			}
			
		}
		while(true){
			int payMethod = Console.askUserInputint("������֧����ʽ(1.�ֽ�	2.֧����)��");
			if(payMethod==1){
				payment.setpMethod("�ֽ�");
				break;
			}else if(payMethod==2){
				payment.setpMethod("֧����");
				break;
			}else{
				Console.println("���벻�Ϸ�");
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
		Console.println("�ܼ�Ϊ��"+totalPrice);
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
