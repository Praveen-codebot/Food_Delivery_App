package com.bob.serviceimplementation;

import java.time.LocalDateTime;
import java.util.List;

import com.bob.dao.OrdersDAO;
import com.bob.model.OrderHistory;
import com.bob.model.OrderItem;
import com.bob.model.Orders;
import com.bob.service.OrderHistoryService;
import com.bob.service.OrderService;


public class OrderServiceImpl implements OrderService{

	private OrdersDAO orderDAO ;
	private OrderHistoryService orderHistoryService;
	
	public OrderServiceImpl( OrdersDAO orderDAO , OrderHistoryService orderHistoryService)
	{
		this.orderDAO = orderDAO;
		this.orderHistoryService = orderHistoryService;
	}
	
	@Override
	public boolean placeOrder(Orders order) {
	
		boolean isOrderPlaced = orderDAO.placeOrder(order);
		
		if(isOrderPlaced)
		
		{
			OrderHistory latest = orderHistoryService.getLatestHistoryForOrder(order.getOrderId());
			
			if(latest == null || !latest.getStatus().equals("Created")) {
			
			OrderHistory history = new OrderHistory();
			history.setOrderId(order.getOrderId());
			history.setStatus("Created");
			history.setChangedAt(LocalDateTime.now());
		
			orderHistoryService.addOrderHistory(history);
		
			System.out.println("Order placed successfully and history recorded.");
			}
			else
			{
				System.out.println("Order already has 'Created' history; no new history added");
			}
		}
		else
		{
			System.out.println("Failed to place order.");
			
		}
		return isOrderPlaced;
	}

	@Override
	public List<Orders> getAllOrdersByUserId(int userId) {

		return orderDAO.getOrderByUser(userId);
	}

	@Override
	public List<Orders> getAllOrders() {

		return orderDAO.getAllOrders();
	}

	@Override
	public boolean updateOrder(int orderId , String status) {

		boolean isUpdated = orderDAO.updateOrderStatus(orderId, status);
		
		if(isUpdated)
		{
			OrderHistory latest = orderHistoryService.getLatestHistoryForOrder(orderId);
			
			if(latest == null || !latest.getStatus().equals(status))
			{
			OrderHistory history = new OrderHistory();
					history.setOrderId(orderId);
					history.setStatus(status);
					history.setChangedAt(LocalDateTime.now());
					
			orderHistoryService.addOrderHistory(history);
			System.out.println("Order status updated and history recorded.");
			
		}
			else
			{
				System.out.println("Order status unchanged ; history not recorded.");
			}
		}
		else
		{
			System.out.println("Failed to update order status.");
		}
		return isUpdated;
	}
	

	@Override

	public boolean deleteOrder(int orderId) {

		boolean isDeleted = orderDAO.deleteOrder(orderId);
		
		if(isDeleted)
		{
			List<OrderHistory> histories = orderHistoryService.getHistoryByOrderId(orderId);
			
			for(OrderHistory h : histories)
			{
				orderHistoryService.deleteOrderHistory(h.getHistoryId());
			}
			System.out.println("Order and its history deleted.");
			
			
		}
		
		return isDeleted;
	}

	@Override
	public Orders getOrderById(int orderId) {

		return orderDAO.getOrderById(orderId);
	}

	@Override
	public boolean placeOrder(Orders order, List<OrderItem> items) {
		  int orderId = orderDAO.saveOrderReturnId(order); // assume this returns the auto-generated orderId
	        if(orderId <= 0) return false;

	        // 2. Save each order item with orderId
	        for(OrderItem item : items){
	            item.setOrderId(orderId);
	            boolean saved = orderDAO.saveOrderItem(item);
	            if(!saved) return false; // if any item fails, return false
	        }

	       

	        return true;
	}

}
