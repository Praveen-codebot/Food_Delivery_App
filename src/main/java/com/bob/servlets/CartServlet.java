package com.bob.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bob.model.Menu;
import com.bob.model.User;
import com.bob.service.UserService;
import com.bob.serviceimplementation.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @SuppressWarnings("unused")
	private UserService userService = new UserServiceImpl();

    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Get current session cart
        List<Menu> cart = (List<Menu>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }

        // Get logged-in user
        User user = (User) session.getAttribute("user");

        // Forward cart and user info to JSP
        request.setAttribute("cartItems", cart);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/jspPages/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(user == null) {
            // Guest users should login first
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Check if user has address
        if(user.getAddress() == null || user.getAddress().trim().isEmpty()) {
            // No address → redirect to add address page
            response.sendRedirect(request.getContextPath() + "/addAddress");
            return;
        }

        // User has address → proceed to checkout/payment
        response.sendRedirect(request.getContextPath() + "/checkout");
    }
}
