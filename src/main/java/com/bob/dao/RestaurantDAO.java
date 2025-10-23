package com.bob.dao;

import java.util.List;

import com.bob.model.Restaurant;

public interface RestaurantDAO {
	
	//Adding new restaurant
	boolean addRestaurant(Restaurant restaurant);
	
	//Getting single restaurant details
	Restaurant getRestaurant(int restaurantId);
	
	// Updating restaurant information
	boolean updateRestaurant(Restaurant restaurant);
	
	// Deleting restaurant by Id
	boolean deleteRestaurant(int restaurantId);
	
	// Getting all restaurants information
	List<Restaurant> getAllRestaurants();
	
	// Getting restaurant by cusisine 
	List<Restaurant> getRestaurantsByCuisine(String cusineType);
	
	//Activate / Deactivate restaurant
	boolean setRestaurantStatus(int restaurantId , boolean isActive);

	//Search  Restaurants
	List<Restaurant> searchRestaurants(String keyword);


}
