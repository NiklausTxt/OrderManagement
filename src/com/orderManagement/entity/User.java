package com.orderManagement.entity;

public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private int clientId=-1;
	private Client client;
	
	
	
	
	public User(String username, String password, String email, int clientId, Client client) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.clientId = clientId;
		this.client = client;
	}
	public User(int id, String username, String password, String email, Client client) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.client = client;
	}
	public User(String username, String password, String email, Client client) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.client = client;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	
	
}
