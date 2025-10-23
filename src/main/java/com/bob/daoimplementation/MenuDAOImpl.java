package com.bob.daoimplementation;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bob.dao.MenuDAO;
import com.bob.model.Menu;
import com.bob.utility.DbConnection;

public class MenuDAOImpl implements MenuDAO {


	@Override
	public boolean addMenuItem(Menu menu) {
		
		
		
		
		String sql ="INSERT INTO menu( restaurant_id , item_name , description , price , ratings , is_available , image_path) "
				+"VALUES(?,?,?,?,?,?,?)";
		
		try(Connection con = DbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)
				) 
		{
			ps.setInt(1,menu.getRestaurantId());
			ps.setString(2, menu.getItemName());
			ps.setString(3, menu.getDescription());
			ps.setDouble(4, menu.getPrice());
			ps.setDouble(5, menu.getRatings());
			ps.setBoolean(6,menu.getIsAvailable());
			ps.setString(7, menu.getImagePath());
			
			return ps.executeUpdate() > 0;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updateMenuItem(Menu menu) {

		String sql ="UPDATE menu "
				+ "SET restaurant_id=?, item_name=? , description=? , price=? , ratings=?, is_available=? , image_path=? "
				+ "WHERE menu_id=?";
		
		try (Connection con = DbConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)	
		   	 ) 
		{
			ps.setInt(1,menu.getRestaurantId());
			ps.setString(2, menu.getItemName());
			ps.setString(3, menu.getDescription());
			ps.setDouble(4, menu.getPrice());
			ps.setDouble(5, menu.getRatings());
			ps.setBoolean(6,menu.getIsAvailable());
			ps.setString(7, menu.getImagePath());
			ps.setInt(8, menu.getMenuId());
			
			return ps.executeUpdate() > 0;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteMenuItem(int menuId) {

		String sql = "DELETE FROM menu WHERE menu_id=?";
		
		
		try (Connection con = DbConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)	)
		{
			ps.setInt(1,menuId);
			
			return ps.executeUpdate() > 0;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Menu getMenuItemById(int menuId) {
		
		String sql = "SELECT * FROM menu WHERE menu_id =?";
		
		try (Connection con = DbConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)
			) 
		{
			ps.setInt(1,menuId);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				return new Menu(
						rs.getInt("menu_id"),
						rs.getInt("restaurant_id"),
						rs.getString("item_name"),
						rs.getString("description"),
						rs.getDouble("price"),
						rs.getDouble("ratings"),
						rs.getBoolean("is_available"),
						rs.getString("image_path")
						);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Menu> getMenuByRestaurantId(int restaurantId) {

		String sql ="SELECT * FROM menu WHERE restaurant_id=?";
		
		List<Menu> menuList = new ArrayList<>();
 		try (Connection con = DbConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) 
		{
			ps.setInt(1,restaurantId);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Menu menu = new Menu(
						rs.getInt("menu_id"),
						rs.getInt("restaurant_id"),
						rs.getString("item_name"),
						rs.getString("description"),
						rs.getDouble("price"),
						rs.getDouble("ratings"),
						rs.getBoolean("is_available"),
						rs.getString("image_path")
						);
				menuList.add(menu);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return menuList;
	}

	@Override
	public List<Menu> getAllMenuItems() {

		String sql = "SELECT * FROM menu";
		List<Menu> menuList = new ArrayList<>();
 		try (Connection con = DbConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) 
		{
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Menu menu = new Menu(
						rs.getInt("menu_id"),
						rs.getInt("restaurant_id"),
						rs.getString("item_name"),
						rs.getString("description"),
						rs.getDouble("price"),
						rs.getDouble("ratings"),
						rs.getBoolean("is_available"),
						rs.getString("image_path")
						);
				menuList.add(menu);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menuList;
	}

}
