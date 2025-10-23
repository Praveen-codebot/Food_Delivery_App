package com.bob.serviceimplementation;

import java.util.List;

import com.bob.dao.OrderItemDAO;
import com.bob.daoimplementation.OrderItemDAOImpl;
import com.bob.model.OrderItem;
import com.bob.service.OrderItemService;

public class OrderItemServiceImpl implements OrderItemService{

	private OrderItemDAO orderItemDAO;
	
	
	
	
	public OrderItemServiceImpl()
	{
		this.orderItemDAO = new OrderItemDAOImpl();
	}
	
	@Override
	public boolean addOrderItem(OrderItem orderItem) {

		return orderItemDAO.addOrderItem(orderItem);
	}

	@Override
	public OrderItem getOrderItemById(int orderItemId) {

		return orderItemDAO.getOrderItemById(orderItemId);
	}

	@Override
	public List<OrderItem> getOrderItemsByOrderId(int orderId) {

		return orderItemDAO.getAllOrderItemsByOrderId(orderId);
	}

	@Override
	public boolean updateOrderItem(OrderItem orderItem) {

		return orderItemDAO.updateOrderItem(orderItem);
	}

	@Override
	public boolean deleteOrderItem(OrderItem orderItem) {

		return orderItemDAO.deleteOrderItem(orderItem);
	}

	@Override
	public List<OrderItem> getAllOrderItems() {

		return orderItemDAO.getAllOrderItems();
	}

}
