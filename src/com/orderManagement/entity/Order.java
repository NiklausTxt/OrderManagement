package com.orderManagement.entity;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private int id;
	private String createTime;
	private String memo = null;
	private String orderStatus = "未支付";
	private int clientId;
	private Payment payment = new Payment();
	private List<OrderItem> items=new ArrayList<>();
	private double totalPrice; 
		
	public Order(int id, String createTime, String orderStatus, int clientId) {
		super();
		this.id = id;
		this.createTime = createTime;
		this.orderStatus = orderStatus;
		this.clientId = clientId;
	}
	public Order(String createTime, int clientId, Payment payment, List<OrderItem> items) {
		super();
		this.createTime = createTime;
		this.clientId = clientId;
		this.payment = payment;
		this.items = items;
	}
	public Order(int id, String createTime, String memo, String orderStatus, int clientId, Payment payment) {
		super();
		this.id = id;
		this.createTime = createTime;
		this.memo = memo;
		this.orderStatus = orderStatus;
		this.clientId = clientId;
		this.payment = payment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	@Override
	public String toString() {
		String payMethod = payment.getpMethod();
		String payStatus = payment.getpStatus();			
		return "订单信息   [编号："+id+ ", 订单状态:" + orderStatus + ", 支付方式:" + payMethod+ ", 支付状态:" + payStatus+",创建时间:" + createTime  + "]";
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
}
