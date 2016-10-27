package com.orderManagement.entity;

public class OrderItem {
	private int id;
	private int amount;
	private String memo = null;
	private int orderId;
	private Product product;
	
	
	
	
	public OrderItem(int id, int amount, String memo, int userId, Product product) {
		super();
		this.id = id;
		this.amount = amount;
		this.memo = memo;
		this.orderId = userId;
		this.product = product;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return product.getName()+" [数量:" + amount+ ",单价:" + product.getPrice() + "]";
	}
	public int getUserId() {
		return orderId;
	}
	public void setUserId(int userId) {
		this.orderId = userId;
	}
	
}
