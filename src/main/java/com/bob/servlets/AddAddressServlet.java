package com.bob.servlets;

import com.bob.model.User;
import com.bob.service.UserService;
import com.bob.serviceimplementation.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/addAddress")
public class AddAddressServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Show Add Address JSP page
        request.getRequestDispatcher("/jspPages/addAddress.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String address = request.getParameter("address");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        if (address == null || address.trim().isEmpty()) {
            request.setAttribute("error", "Address cannot be empty!");
            request.getRequestDispatcher("/jspPages/addAddress.jsp").forward(request, response);
            return;
        }

        // Update address in user object and save in DB
        user.setAddress(address.trim());
        boolean updated = userService.updateAddress(user.getUserId(), address.trim());


        if (updated) {
            // Update session object
            session.setAttribute("user", user);
            // Redirect to cart page to continue placing the order
            response.sendRedirect(request.getContextPath() + "/cart?msg=address_updated");
        } else {
            request.setAttribute("error", "Failed to save address. Try again.");
            request.getRequestDispatcher("/jspPages/addAddress.jsp").forward(request, response);
        }
    }
}
