package com.bob.servlets;

import com.bob.model.Orders;
import com.bob.model.OrderItem;
import com.bob.model.User;
import com.bob.dao.OrdersDAO;
import com.bob.daoimplementation.OrdersDAOImpl;
import com.bob.dao.OrderItemDAO;
import com.bob.daoimplementation.OrderItemDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("serial")
@WebServlet("/viewOrderHistory")
public class ViewOrderHistoryServlet extends HttpServlet {

    private OrdersDAO ordersDAO;
    private OrderItemDAO orderItemDAO;

    @Override
    public void init() throws ServletException {
        ordersDAO = new OrdersDAOImpl();
        orderItemDAO = new OrderItemDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Get the logged-in user
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/jspPages/login.jsp");
            return;
        }

        Integer userId = user.getUserId();

        // Fetch orders for this user
        List<Orders> orderList = ordersDAO.getOrderByUser(userId);

        // Fetch order items for each order
        for (Orders order : orderList) {
            List<OrderItem> items = orderItemDAO.getAllOrderItemsByOrderId(order.getOrderId());
            order.setOrderItems(items);
        }

        // Forward data to JSP
        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("/jspPages/viewOrderHistory.jsp").forward(request, response);
    }
}
