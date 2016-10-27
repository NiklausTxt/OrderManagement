package com.orderManagement.entity;

public class Player {
	private String username;
	private String password;
	private String email;
	private String phone;
	private String nickname;
	private int ClientId=-1;
	private int clientType;
	private int clientStatus;
	private double balance;
	
	
	
	
	public Player(String username, String password, String email, String phone, String nickname, int clientId,
			int clientType) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.nickname = nickname;
		ClientId = clientId;
		this.clientType = clientType;
	}
	public Player(String username, String password, String email, String phone, String nickname, int clientType) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.nickname = nickname;
		this.clientType = clientType;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getClientType() {
		return clientType;
	}
	public void setClientType(int clientType) {
		this.clientType = clientType;
	}
	public int getClientStatus() {
		return clientStatus;
	}
	public void setClientStatus(int clientStatus) {
		this.clientStatus = clientStatus;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getClientId() {
		return ClientId;
	}
	public void setClientId(int clientId) {
		ClientId = clientId;
	}
	
	
}
