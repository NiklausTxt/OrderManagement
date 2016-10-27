package com.orderManagement.entity;

public class Payment {
	private String pMethod=null;
	private String pStatus = "Î´Ö§¸¶";
	
	public Payment() {
		super();
	}
	public Payment(String pMethod, String pStatus) {
		super();
		this.pMethod = pMethod;
		this.pStatus = pStatus;
	}
	public String getpMethod() {
		return pMethod;
	}
	public void setpMethod(String pMethod) {
		this.pMethod = pMethod;
	}
	public String getpStatus() {
		return pStatus;
	}
	public void setpStatus(String pStatus) {
		this.pStatus = pStatus;
	}
}
