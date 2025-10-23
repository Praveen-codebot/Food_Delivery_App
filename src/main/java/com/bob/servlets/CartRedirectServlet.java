package com.bob.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet("/cartRedirect")
public class CartRedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        if (user == null) {
            // Not logged in → redirect to login page
            response.sendRedirect(request.getContextPath() + "/login.jsp?msg=login_required");
        } else {
            // Logged in → show cart
            response.sendRedirect(request.getContextPath() + "/cart");
        }
    }
}
 