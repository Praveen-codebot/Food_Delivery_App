package com.bob.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bob.dao.RestaurantDAO;
import com.bob.model.Restaurant;
import com.bob.utility.DbConnection;

public class RestaurantDAOImpl implements RestaurantDAO{

	@Override
	public boolean addRestaurant(Restaurant restaurant) {
		
		String sql = "INSERT INTO restaurants(name, address, phone , rating , cuisine_type, is_active, eta , admin_user_id , image_path) VALUES(?,?,?,?,?,?,?,?,?)";
		
		try (Connection con =DbConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setString(1, restaurant.getName());
			ps.setString(2, restaurant.getAddress());
			ps.setString(3, restaurant.getPhone());
			ps.setString(4, restaurant.getRating());
			ps.setString(5, restaurant.getCuisineType());
			ps.setBoolean(6, restaurant.getIsActive());
			ps.setString(7, restaurant.getEta());
			ps.setInt(8, restaurant.getAdminUserId());
			ps.setString(9, restaurant.getImagePath());

			return ps.executeUpdate() > 0;
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Restaurant getRestaurant(int restaurantId) {

		String sql = "SELECT * FROM restaurants WHERE restaurant_id=?";
		
		
		try (Connection con = DbConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)
			) 
		{
			ps.setInt(1, restaurantId);
			 ResultSet rs = ps.executeQuery();
			 
			if(rs.next())
			{
				return new Restaurant(
						rs.getInt("restaurant_id"),
						rs.getString("name"),
						rs.getString("address"),
						rs.getString("phone"),
						rs.getString("rating"),
						rs.getString("cuisine_type"),
						rs.getBoolean("is_active"),
						rs.getString("eta"),
						rs.getInt("admin_user_id"),
						rs.getString("image_path")
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
	public boolean updateRestaurant(Restaurant restaurant) {

		String sql = "UPDATE restaurants SET name=? ,address =? , phone =? , rating=? , cuisine_type=? , is_active=? , eta=? , admin_user_id=? , image_path=? WHERE  restaurant_id=?";
		
		
		try (Connection con = DbConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql))
		
		{
			ps.setString(1, restaurant.getName());
			ps.setString(2, restaurant.getAddress());
			ps.setString(3, restaurant.getPhone());
			ps.setString(4, restaurant.getRating());
			ps.setString(5, restaurant.getCuisineType());
			ps.setBoolean(6, restaurant.getIsActive());
			ps.setString(7, restaurant.getEta());
			ps.setInt(8, restaurant.getAdminUserId());
			ps.setString(9, restaurant.getImagePath());
			ps.setInt(10, restaurant.getRestaurantId());
			
			return ps.executeUpdate() > 0;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

	
	@Override
	public boolean deleteRestaurant(int restaurantId) {

		String sql ="DELETE FROM restaurants WHERE restaurant_id=?";
		
		try (Connection con = DbConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)	) 
		{
			ps.setInt(1, restaurantId);
			 return ps.executeUpdate() > 0;
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}

	
	
	@Override
	public List<Restaurant> getAllRestaurants() {

		List<Restaurant> restaurants = new ArrayList<>();
		String sql ="SELECT * FROM restaurants ";
		
		try (Connection con =DbConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) 
		{
			while(rs.next())
			{
				Restaurant restaurant = new Restaurant(
						rs.getInt("restaurant_id"),
						rs.getString("name"),
						rs.getString("address"),
						rs.getString("phone"),
						rs.getString("rating"),
						rs.getString("cuisine_type"),
						rs.getBoolean("is_active"),
						rs.getString("eta"),
						rs.getInt("admin_user_id"),
						rs.getString("image_path")
						);
				restaurants.add(restaurant);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return restaurants;
	}
	

	@Override
	public List<Restaurant> getRestaurantsByCuisine(String cusineType) {

		String sql = "SELECT * FROM restaurants WHERE cuisine_type =?";
		
		List<Restaurant> restaurants = new ArrayList<>();
		try (Connection con = DbConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)
			  ) 
		{
			ps.setString(1, cusineType);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Restaurant restaurant = new Restaurant(
						rs.getInt("restaurant_id"),
						rs.getString("name"),
						rs.getString("address"),
						rs.getString("phone"),
						rs.getString("rating"),
						rs.getString("cuisine_type"),
						rs.getBoolean("is_active"),
						rs.getString("eta"),
						rs.getInt("admin_user_id"),
						rs.getString("image_path")
						);
				restaurants.add(restaurant);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return restaurants;
	}

	
	
	@Override
	public boolean setRestaurantStatus(int restaurantId, boolean isActive) {

		
		String sql = "Update restaurants SET is_active =? WHERE restaurant_id=?";
		
		try (Connection con = DbConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) 
		{
			ps.setBoolean(1, isActive);
			ps.setInt(2, restaurantId);
			
			return ps.executeUpdate() > 0;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<Restaurant> searchRestaurants(String keyword) {
		
		List<Restaurant> list = new ArrayList<>();
		
		String sql="SELECT * FROM restaurants WHERE name LIKE ? OR address LIKE?";
		
		try (Connection con = DbConnection.getConnection() ;
			PreparedStatement ps = con.prepareStatement(sql))
		{
			ps.setString(1, "%" + keyword + "%");
			ps.setString(2, "%" + keyword + "%");
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				Restaurant r = new Restaurant(
						
						rs.getInt("restaurant_id"),
						rs.getString("name"),
						rs.getString("address"),
						rs.getString("phone"),
						rs.getString("rating"),
						rs.getString("cuisine_type"),
						rs.getBoolean("is_active"),
						rs.getString("eta"),
						rs.getInt("admin_user_id"),
						rs.getString("image_path")
						);
				list.add(r);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	

}
