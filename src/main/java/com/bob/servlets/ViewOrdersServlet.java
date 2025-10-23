package com.bob.servlets;

import com.bob.model.Orders;
import com.bob.service.OrderService;
import com.bob.serviceimplementation.OrderServiceImpl;
import com.bob.dao.OrdersDAO;
import com.bob.daoimplementation.OrdersDAOImpl;
import com.bob.service.OrderHistoryService;
import com.bob.serviceimplementation.OrderHistoryServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/viewOrders")
public class ViewOrdersServlet extends HttpServlet {

    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        OrdersDAO ordersDAO = new OrdersDAOImpl();
        OrderHistoryService orderHistoryService = new OrderHistoryServiceImpl();
        orderService = new OrderServiceImpl(ordersDAO, orderHistoryService);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            List<Orders> orders = orderService.getAllOrdersByUserId(userId);
            request.setAttribute("ordersList", orders);
            request.getRequestDispatcher("orders.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error fetching orders: " + e.getMessage());
        }
    }
}
