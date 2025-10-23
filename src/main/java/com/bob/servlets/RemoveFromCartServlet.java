package com.bob.servlets;

import java.io.IOException;
import java.util.List;

import com.bob.model.Menu;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/removeFromCart")
public class RemoveFromCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menuIdParam = request.getParameter("menuId");
        if (menuIdParam == null) {
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        int menuId = Integer.parseInt(menuIdParam);

        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<Menu> cart = (List<Menu>) session.getAttribute("cart");

        if (cart != null) {
            cart.removeIf(item -> item.getMenuId() == menuId);
            session.setAttribute("cart", cart);
        }

        response.sendRedirect(request.getContextPath() + "/cart");
    }
}
