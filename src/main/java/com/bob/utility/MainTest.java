package com.bob.utility;


import java.sql.Timestamp;

import java.util.Date;
import java.util.List;

import com.bob.dao.OrdersDAO;
import com.bob.daoimplementation.OrdersDAOImpl;
import com.bob.model.OrderHistory;
import com.bob.model.Orders;
import com.bob.service.OrderHistoryService;
import com.bob.service.OrderService;
import com.bob.serviceimplementation.OrderHistoryServiceImpl;
import com.bob.serviceimplementation.OrderServiceImpl;

public class MainTest {
	static Date orderDate = new Timestamp(System.currentTimeMillis());

    public static void main(String[] args) {

        OrdersDAO ordersDAO = new OrdersDAOImpl();
        OrderHistoryService orderHistoryService = new OrderHistoryServiceImpl();
        OrderService orderService = new OrderServiceImpl(ordersDAO, orderHistoryService);

        // Place a new order
        
        
        Orders newOrder = new Orders(0, 1, 2, orderDate, 499.99, "Pending", "Cash", null);
        boolean placed = orderService.placeOrder(newOrder);
        System.out.println("Order placed: " + placed);

        // Update order status multiple times
        orderService.updateOrder(newOrder.getOrderId(), "Confirmed");
        orderService.updateOrder(newOrder.getOrderId(), "Dispatched");
        orderService.updateOrder(newOrder.getOrderId(), "Delivered");

        // Fetch and display single order with histories
        Orders fetchedOrder = orderService.getOrderById(newOrder.getOrderId());
        System.out.println("\nFetched Order: " + fetchedOrder);

        List<OrderHistory> histories = orderHistoryService.getHistoryByOrderId(newOrder.getOrderId());
        System.out.println("\nOrder Histories:");
        for (OrderHistory h : histories) {
            System.out.println(h);
        }

        // Display all orders for a user with grouped histories
        System.out.println("\nAll orders for user 1:");
        List<Orders> userOrders = orderService.getAllOrdersByUserId(1);
        for (Orders order : userOrders) {
            System.out.println(order);
            List<OrderHistory> orderHistories = orderHistoryService.getHistoryByOrderId(order.getOrderId());
            for (OrderHistory h : orderHistories) {
                System.out.println("  " + h);
            }
            System.out.println("------------------------------------------------");
        }

        // Delete the test order
        boolean deleted = orderService.deleteOrder(newOrder.getOrderId());
        System.out.println("Order deleted: " + deleted);
    }
}
