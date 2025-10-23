package com.bob.servlets;

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

@WebServlet("/deleteOrder")
public class DeleteOrderServlet extends HttpServlet {

    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        OrdersDAO ordersDAO = new OrdersDAOImpl();
        OrderHistoryService orderHistoryService = new OrderHistoryServiceImpl();
        orderService = new OrderServiceImpl(ordersDAO, orderHistoryService);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            boolean deleted = orderService.deleteOrder(orderId);
            request.setAttribute("orderDeleted", deleted);
            request.getRequestDispatcher("deleteOrder.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error deleting order: " + e.getMessage());
        }
    }
}
