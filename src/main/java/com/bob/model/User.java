package com.bob.model;

import java.time.LocalDateTime;

public class User {

	private int userId;
	private String username;
	private String password;
	private String email;
	private String phone;
	private String address;
	private LocalDateTime createdAt;
	
	
	
	public User()
	{
		
	}


	public User(int userId, String username, String password, String email, String phone, String address,
			 LocalDateTime createdAt) 
	{
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.createdAt = createdAt;
		
		
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return username;
	}


	public void setUserName(String username) {
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


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}



	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public static void main(String[] args) 
	{
	
		
	}
	
	
}
