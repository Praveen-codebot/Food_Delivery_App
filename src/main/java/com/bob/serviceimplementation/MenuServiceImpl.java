package com.bob.serviceimplementation;

import java.util.List;

import com.bob.dao.MenuDAO;
import com.bob.dao.RestaurantDAO;
import com.bob.daoimplementation.MenuDAOImpl;
import com.bob.daoimplementation.RestaurantDAOImpl;
import com.bob.model.Menu;
import com.bob.model.Restaurant;
import com.bob.service.MenuService;

public class MenuServiceImpl implements MenuService{

	private MenuDAO menuDAO;
	
	public MenuServiceImpl()
	{
	this.menuDAO = new MenuDAOImpl();
	}
	
	@Override
	public boolean addMenu(Menu menu) {

		return menuDAO.addMenuItem(menu);
	}

	@Override
	public Menu getMenuById(int menuId) {
		return menuDAO.getMenuItemById(menuId);
	}

	@Override
	public List<Menu> getAllMenus() {

		return menuDAO.getAllMenuItems();
	}

	@Override
	public List<Menu> getMenuByRestaurantId(int restaurantId) {

		return menuDAO.getMenuByRestaurantId(restaurantId);
	}

	@Override
	public boolean updateMenu(Menu menu) {
		return menuDAO.updateMenuItem(menu);
	}

	@Override
	public boolean deleteMenu(int menuId) {
		return menuDAO.deleteMenuItem(menuId);
	}

	@Override
	public Restaurant getRestaurantById(int restaurantId) {
	    RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
	    return restaurantDAO.getRestaurant(restaurantId); // DAO fetches from DB
	}

}
