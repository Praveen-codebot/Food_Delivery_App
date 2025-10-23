package com.bob.service;

import java.util.List;

import com.bob.model.OrderItem;

public interface OrderItemService {
	boolean addOrderItem(OrderItem orderItem);
	OrderItem getOrderItemById(int orderItemId);
	List<OrderItem> getOrderItemsByOrderId(int orderId);
	boolean updateOrderItem(OrderItem orderItem);
	boolean deleteOrderItem(OrderItem orderItem);
	List<OrderItem> getAllOrderItems();
}
