package com.bob.dao;

import java.util.List;

import com.bob.model.User;


public interface UserDAO 
{
	// Registering new User
	boolean addUser(User user);
	
	//login check
	User loginUser(String email, String password);
	
	//email checking
	boolean isEmailExists(String email);
	
	// Login with email and password 
	User getUserByEmail(String email);
	
	// getting single user information
	User getUserById(int userId);
	
	// updating single user 
	boolean updateUser(User user);
	
	//deleting single user
	boolean deleteUser(int userId);
	
	// getting all users information
	List<User> getAllUsers();
	
	boolean updateAddress(int userId,String address);
	
}


	
