package com.bob.service;

import java.util.List;

import com.bob.model.Restaurant;

public interface RestaurantService {
	
	boolean addRestaurant(Restaurant restaurant);
	
	// Get restaurant by id
	Restaurant getRestaurantById(int restaurantId);
	
	//Get all Restaurants
	List<Restaurant> getRestaurants();
	
	//Get restaurant by cuisine type
	List<Restaurant> getRestaurantByCuisineType(String cuisine);
	
	//Update Restaurant
	boolean updateRestaurant(Restaurant restaurant);
	
	//Delete Restaurant
	boolean deleteRestaurant(int restaurantId);
	
	//Search restaurants
	List<Restaurant> searchRestaurants(String keyword);

}
