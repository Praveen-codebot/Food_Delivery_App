package com.bob.dao;

import java.util.List;

import com.bob.model.OrderItem;

public interface OrderItemDAO {
	
	// Add item to an order
	boolean addOrderItem(OrderItem orderItem);
	
	//Update Oder item 
	boolean updateOrderItem(OrderItem orderItem);
	
	//Delete Order item
	boolean deleteOrderItem(OrderItem orderItem);
	
	//get order item by Id
	OrderItem getOrderItemById(int orderItemId);
	
	//Get all orderItems for a specific order
	List<OrderItem> getAllOrderItemsByOrderId(int orderId);
	
	//Get all orderItems (optional for admin)
	List<OrderItem> getAllOrderItems();

}
