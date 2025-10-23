package com.bob.servlets;

import java.io.IOException;
import java.util.List;
import com.bob.dao.UserDAO;
import com.bob.daoimplementation.UserDAOImpl;
import com.bob.model.Menu;
import com.bob.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.loginUser(email, password);

        HttpSession session = request.getSession();

        if(user != null) {
            // Get pre-login cart
            @SuppressWarnings("unchecked")
            List<Menu> preLoginCart = (List<Menu>) session.getAttribute("cart");

            // Set user in session
            session.setAttribute("user", user);

            // Merge pre-login cart into session cart without doubling
            if (preLoginCart != null) {
                @SuppressWarnings("unchecked")
                List<Menu> sessionCart = (List<Menu>) session.getAttribute("cart");
                if (sessionCart == null) {
                    sessionCart = preLoginCart;
                } else {
                    for (Menu preItem : preLoginCart) {
                        boolean exists = false;
                        for (Menu sItem : sessionCart) {
                            if (sItem.getMenuId() == preItem.getMenuId()) {
                                // Replace quantity instead of adding
                                sItem.setQuantity(preItem.getQuantity());
                                exists = true;
                                break;
                            }
                        }
                        if (!exists) sessionCart.add(preItem);
                    }
                }
                session.setAttribute("cart", sessionCart);
            }

            // Redirect to intended page if stored (e.g., cart)
            String redirectAfterLogin = (String) session.getAttribute("loginRedirect");
            if (redirectAfterLogin != null) {
                session.removeAttribute("loginRedirect");
                response.sendRedirect(redirectAfterLogin);
            } else {
                response.sendRedirect(request.getContextPath() + "/home");
            }

        } else {
            // invalid login
            response.sendRedirect(request.getContextPath() + "/login?msg=invalid");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jspPages/login.jsp").forward(request, response);
    }
}
