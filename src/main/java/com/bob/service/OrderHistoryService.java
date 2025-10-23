package com.bob.service;

import java.util.List;

import com.bob.model.OrderHistory;
import com.bob.model.Orders;

public interface OrderHistoryService {
	
	boolean addOrderHistory(OrderHistory orderHistory);
	
	OrderHistory getOrderHistoryById(int histroyId);
	
	OrderHistory getLatestHistoryForOrder(int orderId);
	
	List<OrderHistory> getHistoryByOrderId(int orderId);
	
	List<OrderHistory> getOrderHistoryByUserId(int userId);
	
	List<OrderHistory> getAllOrderHistories();
	
	boolean deleteOrderHistory(int historyId);
	

}
