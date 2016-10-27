package com.orderManagement.entity;

public class Product {
	private int id;
	private String code;
	private String name;
	private double price;
	private String pUnit;
	private String pCatalog;
	
	
	
	public Product(int id, String code, String name, double price, String pUnit, String pCatalog) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.price = price;
		this.pUnit = pUnit;
		this.pCatalog = pCatalog;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getpUnit() {
		return pUnit;
	}
	public void setpUnit(String pUnit) {
		this.pUnit = pUnit;
	}
	public String getpCatalog() {
		return pCatalog;
	}
	public void setpCatalog(String pCatalog) {
		this.pCatalog = pCatalog;
	}
	@Override
	public String toString() {
		return "Product [code=" + code + ", name=" + name + ", price=" + price + ", pUnit=" + pUnit + ", pCatalog="
				+ pCatalog + "]";
	}
	
	
	
}
