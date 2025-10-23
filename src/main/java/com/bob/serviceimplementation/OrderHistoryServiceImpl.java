package com.bob.serviceimplementation;

import java.util.List;

import com.bob.dao.OrderHistoryDAO;
import com.bob.daoimplementation.OrderHistoryDAOImpl;
import com.bob.model.OrderHistory;
import com.bob.service.OrderHistoryService;

public class OrderHistoryServiceImpl implements OrderHistoryService {

	private OrderHistoryDAO orderHistoryDAO;
	
	public OrderHistoryServiceImpl()
	{
		this.orderHistoryDAO = new OrderHistoryDAOImpl();
	}

	@Override
	public boolean addOrderHistory(OrderHistory orderHistory) {
		
		
		return orderHistoryDAO.addOrderHistory(orderHistory);
	}

	@Override
	public OrderHistory getOrderHistoryById(int histroyId) {
		return orderHistoryDAO.getOrderHistoryById(histroyId);
	}

	@Override
	public OrderHistory getLatestHistoryForOrder(int orderId) {

		return orderHistoryDAO.getLatestHistoryForOrder(orderId);
	}

	@Override
	public List<OrderHistory> getHistoryByOrderId(int orderId) {

		return orderHistoryDAO.getHistoryByOrderId(orderId);
	}

	@Override
	public List<OrderHistory> getOrderHistoryByUserId(int userId) {

		return orderHistoryDAO.getOrderHistoryForUser(userId);
	}

	@Override
	public List<OrderHistory> getAllOrderHistories() {

		return orderHistoryDAO.getAllOrderHistories();
	}

	@Override
	public boolean deleteOrderHistory(int historyId) {

		return orderHistoryDAO.deleteOrderHistory(historyId);
	}
	



}
