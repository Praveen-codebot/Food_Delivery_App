package com.bob.serviceimplementation;

import java.util.List;

import com.bob.dao.UserDAO;
import com.bob.daoimplementation.UserDAOImpl;
import com.bob.model.User;
import com.bob.service.UserService;

public class UserServiceImpl implements UserService{

	

	private UserDAO userDAO;
	public UserServiceImpl()
	{
		this.userDAO = new UserDAOImpl();
	}
	
	@Override
	public boolean registerUser(User user) {

		return userDAO.addUser(user);
	}

	@Override
	public User loginUser(String email, String password) {

		User user = userDAO.getUserByEmail(email);
		if(user != null)
		{
			if(user.getPassword().equals(password))
			{
				return user;
			}
			else
			{
				System.out.print("Incorrect password");
				return null;
			}
		}
		else
		{
			System.out.println("Email not found");
			return null;
		}
		
	}

	@Override
	public User getUserById(int userId) {

		return userDAO.getUserById(userId);
	
	}

	@Override
	public List<User> getAllUsers() {
		
		return userDAO.getAllUsers();
	}

	@Override
	public boolean updateUser(User user) {
		return userDAO.updateUser(user);
		
	}

	@Override
	public boolean deleteUser(int userId) {

		return userDAO.deleteUser(userId);
	}

	@Override
	public boolean updateAddress(int userId, String address) {
	    return userDAO.updateAddress(userId, address);
	}


}
