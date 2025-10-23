package com.bob.servlets;

import java.io.IOException;
import java.util.List;
import com.bob.model.Menu;
import com.bob.service.MenuService;
import com.bob.serviceimplementation.MenuServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private MenuService menuService;

    @Override
    public void init() throws ServletException {
        menuService = new MenuServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String restaurantIdParam = request.getParameter("restaurantId");

        if (restaurantIdParam == null || restaurantIdParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/restaurants");
            return;
        }

        try {
            int restaurantId = Integer.parseInt(restaurantIdParam);
            List<Menu> menuItems = menuService.getMenuByRestaurantId(restaurantId);

            if (menuItems == null || menuItems.isEmpty()) {
                request.setAttribute("message", "No menu items available for this restaurant.");
            } else {
                request.setAttribute("menuItems", menuItems);
            }

            // Save restaurantId in session for cart later
            HttpSession session = request.getSession();
            session.setAttribute("currentRestaurantId", restaurantId);

            request.getRequestDispatcher("/jspPages/menu.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/restaurants");
        }
    }
}
