package com.bob.servlets;

import com.bob.model.Restaurant;
import com.bob.model.Menu;
import com.bob.service.MenuService;
import com.bob.service.RestaurantService;
import com.bob.serviceimplementation.MenuServiceImpl;
import com.bob.serviceimplementation.RestaurantServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("serial")
@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private RestaurantService restaurantService;
    private MenuService menuService;

    @Override
    public void init() throws ServletException {
        restaurantService = new RestaurantServiceImpl();
        menuService = new MenuServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        // Fetch restaurants
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        request.setAttribute("restaurants", restaurants);

        // If logged in, fetch popular menu items
        if (user != null) {
            List<Menu> popularMenus = menuService. getAllMenus(); // you can create this method
            request.setAttribute("popularMenus", popularMenus);
        }

        request.getRequestDispatcher("/jspPages/home.jsp").forward(request, response);
    }


}
