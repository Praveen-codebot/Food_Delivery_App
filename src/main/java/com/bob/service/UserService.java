package com.bob.service;

import java.util.List;

import com.bob.model.User;


public interface UserService {
	
	// New user registration
	boolean registerUser(User user);

	// User Login service
	User loginUser(String email , String password);
	
	//Fetchinf user by it
	User getUserById(int userId);
	
	//Fetching all users 
	List<User> getAllUsers();
	
	//updating user 
	boolean updateUser(User user);
	
	//delete user
	boolean deleteUser(int userId);
	
	boolean updateAddress(int userId, String address);

	
	
}
