package com.bob.servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.bob.model.Menu;
import com.bob.model.OrderItem;
import com.bob.model.Orders;
import com.bob.model.Restaurant;
import com.bob.model.User;
import com.bob.service.MenuService;
import com.bob.service.OrderService;
import com.bob.service.OrderHistoryService;
import com.bob.serviceimplementation.MenuServiceImpl;
import com.bob.serviceimplementation.OrderServiceImpl;
import com.bob.serviceimplementation.OrderHistoryServiceImpl;
import com.bob.dao.OrdersDAO;
import com.bob.daoimplementation.OrdersDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/placeOrder")
public class PlaceOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private OrderService orderService;
    private MenuService menuService;
    private OrderHistoryService orderHistoryService;

    @Override
    public void init() throws ServletException {
        OrdersDAO ordersDAO = new OrdersDAOImpl();
        orderHistoryService = new OrderHistoryServiceImpl();
        orderService = new OrderServiceImpl(ordersDAO, orderHistoryService);
        menuService = new MenuServiceImpl();
    }

    // Redirect GET requests to cart to avoid 404
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/cart");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // 1️⃣ User must be logged in
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            session.setAttribute("loginRedirect", "/cart");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // 2️⃣ Cart validation
        @SuppressWarnings("unchecked")
        List<Menu> cart = (List<Menu>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/cart?msg=empty_cart");
            return;
        }
        
        // checking address for new user
        if (user.getAddress() == null || user.getAddress().trim().isEmpty())
        {
            // Redirect to an address update page before placing the order
            response.sendRedirect(request.getContextPath() + "/jspPages/addAddress.jsp");
            return;
        }

        // 3️⃣ Restaurant selection
        Integer restaurantId = (Integer) session.getAttribute("currentRestaurantId");
        if (restaurantId == null) {
            response.sendRedirect(request.getContextPath() + "/restaurants?msg=select_restaurant");
            return;
        }

        // 4️⃣ Payment mode
        String paymentMode = request.getParameter("paymentMode");
        if (paymentMode == null || paymentMode.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/cart?msg=select_payment");
            return;
        }

        try {
            // 5️⃣ Calculate total amount
            double totalAmount = 0;
            for (Menu item : cart) {
                totalAmount += item.getPrice() * item.getQuantity();
            }

            // 6️⃣ Create Orders object
            Orders newOrder = new Orders();
            newOrder.setUserId(user.getUserId());
            newOrder.setRestaurantId(restaurantId);
            newOrder.setOrderDate(Date.valueOf(LocalDate.now()));
            newOrder.setTotalAmount(totalAmount);
            newOrder.setStatus("Created");
            newOrder.setPaymentMode(paymentMode);
            newOrder.setAddress(user.getAddress());

            // 7️⃣ Convert cart items to OrderItems
            List<OrderItem> orderItems = new ArrayList<>();
            for (Menu item : cart) {
                OrderItem oi = new OrderItem();
                oi.setMenuId(item.getMenuId());
                oi.setItemName(item.getItemName());
                oi.setQuantity(item.getQuantity());
                oi.setTotalPrice(item.getPrice() * item.getQuantity());
                orderItems.add(oi);
            }

            // 8️⃣ Place order
            boolean success = orderService.placeOrder(newOrder, orderItems);

            if (success) {
                // 9️⃣ Clear cart only after successful order
                session.removeAttribute("cart");

                // 10️⃣ Redirect to cart with success message
                response.sendRedirect(request.getContextPath() + "/cart?msg=success_order");
                return;

            } else {
                response.sendRedirect(request.getContextPath() + "/cart?msg=fail_order");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/cart?msg=fail_order");
        }
    }
}
