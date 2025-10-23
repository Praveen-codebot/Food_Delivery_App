package com.bob.service;

import java.util.List;

import com.bob.model.OrderItem;
import com.bob.model.Orders;


public interface OrderService {

	//Place New Order
	boolean placeOrder(Orders order);
	
	boolean placeOrder(Orders order , List<OrderItem> items);
	
	Orders getOrderById(int orderId);
	
	//Get all orders by id
	List<Orders> getAllOrdersByUserId(int userId);
	
	//Get all orders
	List<Orders> getAllOrders();
	
	//update Order
	boolean updateOrder(int orderId , String status);

	//delete Order
	boolean deleteOrder(int orderID);
	
}
