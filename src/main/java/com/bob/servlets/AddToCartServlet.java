package com.bob.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bob.model.Menu;
import com.bob.model.User;
import com.bob.service.MenuService;
import com.bob.serviceimplementation.MenuServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MenuService menuService;

    @Override
    public void init() throws ServletException {
        menuService = new MenuServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int menuId = Integer.parseInt(request.getParameter("menuId"));
        int quantity = 1;
        try {
            quantity = Integer.parseInt(request.getParameter("quantity"));
            if (quantity < 1) quantity = 1;
        } catch (NumberFormatException e) {
            quantity = 1;
        }

        HttpSession session = request.getSession();

        // Get cart from session
        @SuppressWarnings("unchecked")
        List<Menu> cart = (List<Menu>) session.getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();

        // Check if item already exists in cart
        Menu existing = null;
        for (Menu m : cart) {
            if (m.getMenuId() == menuId) {
                existing = m;
                break;
            }
        }

        if (existing != null) {
            // Add quantity to existing item
            existing.setQuantity(existing.getQuantity() + quantity);
        } else {
            // Fetch menu item from service and set quantity
            Menu menuItem = menuService.getMenuById(menuId);
            if (menuItem != null) {
                menuItem.setQuantity(quantity);
                cart.add(menuItem);
            }
        }

        session.setAttribute("cart", cart);

        // Redirect back to menu page
        Integer currentRestaurantId = (Integer) session.getAttribute("currentRestaurantId");
        if (currentRestaurantId != null) {
            response.sendRedirect(request.getContextPath() + "/menu?restaurantId=" + currentRestaurantId);
        } else {
            response.sendRedirect(request.getContextPath() + "/restaurants");
        }
    }
}
