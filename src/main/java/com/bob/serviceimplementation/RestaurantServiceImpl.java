package com.bob.serviceimplementation;

import java.util.List;

import com.bob.dao.RestaurantDAO;
import com.bob.daoimplementation.RestaurantDAOImpl;
import com.bob.model.Restaurant;
import com.bob.service.RestaurantService;

public class RestaurantServiceImpl implements RestaurantService {

	
	private RestaurantDAO restaurantDAO;
	
	public RestaurantServiceImpl()
	{
		this.restaurantDAO = new RestaurantDAOImpl();
	}
	
	@Override
	public boolean addRestaurant(Restaurant restaurant) {

		return restaurantDAO.addRestaurant(restaurant);
	}

	@Override
	public Restaurant getRestaurantById(int restaurantId) {

		return restaurantDAO.getRestaurant(restaurantId);
	}

	@Override
	public List<Restaurant> getRestaurants() {

		return restaurantDAO.getAllRestaurants();
	}

	@Override
	public List<Restaurant> getRestaurantByCuisineType(String cuisine) {

		return restaurantDAO.getRestaurantsByCuisine(cuisine);
	}

	@Override
	public boolean updateRestaurant(Restaurant restaurant) {

		return restaurantDAO.updateRestaurant(restaurant);
	}

	@Override
	public boolean deleteRestaurant(int restaurantId) {

		return restaurantDAO.deleteRestaurant(restaurantId);
	}

	@Override
	public List<Restaurant> searchRestaurants(String keyword) {
		
		return restaurantDAO.searchRestaurants(keyword);
	}

}
