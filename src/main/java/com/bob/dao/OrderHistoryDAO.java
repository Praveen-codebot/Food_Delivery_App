package com.bob.dao;

import java.util.List;

import com.bob.model.OrderHistory;

public interface OrderHistoryDAO {
	
	//add a new history entry
	boolean addOrderHistory(OrderHistory history);
	
	// Get a single history by its primary key 
	OrderHistory getOrderHistoryById(int historyId);
	
	// Get the latest status update for an order
		OrderHistory getLatestHistoryForOrder(int orderId);
	
	// Get all History records for a specific order
	List<OrderHistory> getHistoryByOrderId(int orderId);
	
	//Get all histories for a specific user
	List<OrderHistory> getOrderHistoryForUser(int userId);
	
	//Get all order histories (optional for admin)
	List<OrderHistory> getAllOrderHistories();
	
	// delete Order history by id
	
	boolean deleteOrderHistory(int historyId);


}
