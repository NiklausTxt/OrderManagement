package com.orderManagement.entity;

public class Client {
	private int id;
	private String nickname;
	private String phone;
	private int clientType;
	private int clientStatus;
	
	
	public Client(int id, String nickname, String phone, int clientType, int clientStatus) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.phone = phone;
		this.clientType = clientType;
		this.clientStatus = clientStatus;
	}
	public Client(String nickname, String phone, int clientType) {
		super();
		this.nickname = nickname;
		this.phone = phone;
		this.clientType = clientType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	@Override
	public String toString() {
		return "Client [id=" + id + ", nickname=" + nickname + ", phone=" + phone + ", clientType=" + clientType
				+ ", clientStatus=" + clientStatus + "]";
	}
	
	
	
}
