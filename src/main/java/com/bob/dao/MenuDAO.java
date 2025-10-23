package com.bob.dao;

import java.util.List;

import com.bob.model.Menu;

public interface MenuDAO {
	
	//Insert new Menu Item
	boolean addMenuItem(Menu menu);
	
	//Update an existing Menu item
	boolean updateMenuItem(Menu menu);
	
	//Delete an existing menu item\
	boolean deleteMenuItem(int menuId);

	//Get a menu item by Id
	Menu getMenuItemById(int menuId);
	
	// Get all menu items of a restaurant
	List<Menu> getMenuByRestaurantId(int restaurantId);

	
	//Get all menu items (optional for admin)
	
	List<Menu> getAllMenuItems();

}
