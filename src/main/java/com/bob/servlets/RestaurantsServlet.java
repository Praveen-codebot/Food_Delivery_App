package com.bob.servlets;

import com.bob.model.Restaurant;
import com.bob.service.RestaurantService;
import com.bob.serviceimplementation.RestaurantServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/restaurants")
public class RestaurantsServlet extends HttpServlet {

    private RestaurantService restaurantService;

    @Override
    public void init() {
        restaurantService = new RestaurantServiceImpl(); // make sure this fetches data from DB
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String search = request.getParameter("search");

        List<Restaurant> restaurants;
        if (search != null && !search.isEmpty()) {
            restaurants = restaurantService.searchRestaurants(search); // filtered by search
        } else {
            restaurants = restaurantService.getRestaurants(); // all restaurants
        }

        request.setAttribute("restaurants", restaurants);
        request.setAttribute("search", search); // to retain search input
        request.getRequestDispatcher("/jspPages/restaurants.jsp").forward(request, response);
    }
}
