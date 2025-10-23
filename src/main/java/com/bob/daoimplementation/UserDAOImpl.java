package com.bob.daoimplementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.bob.dao.UserDAO;
import com.bob.model.User;
import com.bob.utility.DbConnection;

public class UserDAOImpl implements UserDAO{
	
	private Connection con;
	
	public UserDAOImpl()
	{
		this.con = DbConnection.getConnection();
	}
	
	
	@Override
	public boolean addUser(User user) {
		
		String sql = "INSERT INTO users(username,password,email,phone,address) VALUES(?,?,?,?,?)";
		
		try(PreparedStatement ps = con.prepareStatement(sql);	) 
		
		
		{
			
			ps.setString(1,user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3,user.getEmail());
			ps.setString(4, user.getPhone());
			ps.setString(5, user.getAddress() == null ? "" : user.getAddress());
			
			
			return ps.executeUpdate() > 0;
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public User getUserByEmail(String email) {

		String sql = "SELECT * FROM users WHERE email = ?";
		
		
		try(PreparedStatement ps = con.prepareStatement(sql);	) 
		
		{
			ps.setString(1, email);
			
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				return new User(
						rs.getInt("user_id"),
						rs.getString("username"),
						rs.getString("password"),
						rs.getString("email"),
						rs.getString("phone"),
						rs.getString("address"),
						rs.getTimestamp("createdAt").toLocalDateTime()
						);
			}
			
		} 
		catch (Exception e)
		{
		e.printStackTrace();
		}
		return null;
	}

	
	
	
	@Override
	public User getUserById(int userId) {
		
		String sql ="SELECT * FROM users WHERE user_id=?";
		
		
		try (PreparedStatement ps = con.prepareStatement(sql))
		
		{
			ps.setInt(1, userId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				return new User(
				rs.getInt("user_id"),
				rs.getString("username"),
				rs.getString("password"),
				rs.getString("email"),
				rs.getString("phone"),
				rs.getString("address"),
				rs.getTimestamp("createdAt").toLocalDateTime()
				);
			}
			
		} 
		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}

	
	
	
	@Override
	public boolean updateUser(User user) {

		String sql = "Update users SET username =? , email=? , password=?, phone=?, address=? WHERE user_id=?";
		
		try (PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getPhone());
			ps.setString(5, user.getAddress() == null ? "" : user.getAddress());
			ps.setInt(6, user.getUserId());
			
			return ps.executeUpdate() > 0 ;
			
		}
		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}

	
	
	
	@Override
	public boolean deleteUser(int userId) {

		String sql ="DELETE FROM users WHERE user_id=?";
		
		try (PreparedStatement ps = con.prepareStatement(sql))
		{
			
			ps.setInt(1, userId);
			return ps.executeUpdate() > 0 ;
		}
		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		
		return false;
	}

	@Override
	public List<User> getAllUsers() {

		ArrayList<User> users = new ArrayList<>();
		String sql ="SELECT * FROM users";
		
		try (Statement ps = con.createStatement();
			 ResultSet rs =	ps.executeQuery(sql))
		{
			while(rs.next())
			{
				users.add(new User(
						
						rs.getInt("user_id"),
						rs.getString("username"),
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("phone"),
						rs.getString("address"),
						rs.getTimestamp("createdAt").toLocalDateTime()
						
						));
			}
		} 
		
		catch (Exception e)  
		{
			e.printStackTrace();
		}
		
		return users;
	}


	@Override
	public User loginUser(String email, String password) {
	
		User user =null;
		String sql= "SELECT * FROM users WHERE email=? AND password=?";
	
		try (PreparedStatement ps =con.prepareStatement(sql))
		{
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs= ps.executeQuery();
			
			if(rs.next())
			{
				 user =  new User(
						rs.getInt("user_id"),
						rs.getString("username"),
						rs.getString("password"),
						rs.getString("email"),
						rs.getString("phone"),
						rs.getString("address"),
						rs.getTimestamp("createdAt").toLocalDateTime()
						);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}


	@Override
	public boolean isEmailExists(String email) {
		  boolean exists = false;
		    String sql = "SELECT * FROM users WHERE email = ?";

		    try (Connection conn = DbConnection.getConnection();
		         PreparedStatement ps = conn.prepareStatement(sql)) {
		        ps.setString(1, email);
		        ResultSet rs = ps.executeQuery();
		        if (rs.next()) {
		            exists = true; // email found
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return exists;
	}


	@Override
	public boolean updateAddress(int userId, String address) {
	    String sql = "UPDATE users SET address = ? WHERE user_id = ?";
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, address);
	        ps.setInt(2, userId);
	        return ps.executeUpdate() > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}


}
