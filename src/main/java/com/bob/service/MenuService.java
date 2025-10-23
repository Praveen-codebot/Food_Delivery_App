package com.bob.service;

import java.util.List;

import com.bob.model.Menu;
import com.bob.model.Restaurant;

public interface MenuService {
	
	//Adding new menu item
	boolean addMenu(Menu menu);
	
	//Get menu by id
	Menu getMenuById(int menuId);
	
	//Get all menu items 
	 List<Menu> getAllMenus();
	 
	 // Get menu for specific restaurants 
	 List<Menu> getMenuByRestaurantId(int restaurantId);
	 
	 //Update Menu
	 boolean updateMenu(Menu menu);
	 
	 //Delete Menu
	 boolean deleteMenu(int menuId);
	 
	 Restaurant getRestaurantById(int restaurantId);
	
}
