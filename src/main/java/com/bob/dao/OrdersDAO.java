package com.bob.dao;

import java.util.List;

import com.bob.model.OrderItem;
import com.bob.model.Orders;

public interface OrdersDAO {
	
	//Place new Order
	boolean placeOrder(Orders order);
	
	boolean placeOrder(Orders order, List<OrderItem> orderItems );
	
	//Update order status(Pending , Confirmed , Delivered , Cancelled)
	boolean updateOrderStatus(int orderId, String status);

	
	//Get order by Id
	Orders getOrderById(int orderId);
	
	//Get orders by User
	List<Orders>getOrderByUser(int userId);

	// Get all orders by restaurant
	List<Orders>getOrderByRestaurantId(int restaurantId);

	
	//Get all orders (optional , for admin)
	
	List<Orders> getAllOrders();
	
	//delete order
	boolean deleteOrder(int orderId);
	
	int saveOrderReturnId(Orders order);  // returns generated orderId
   
	boolean saveOrderItem(OrderItem item);
}
