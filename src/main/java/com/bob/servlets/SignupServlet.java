package com.bob.servlets;

import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmpassword");

        UserDAO userDAO = new UserDAOImpl();

        // Basic validation
        if (!password.equals(confirmPassword)) {
            response.sendRedirect(request.getContextPath() + "/signup?msg=password_mismatch");
            return;
        }

        // Check if email already exists
        if (userDAO.isEmailExists(email)) {
            response.sendRedirect(request.getContextPath() + "/signup?msg=email_exists");
            return;
        }

        // Create new user
        User user = new User();
        user.setUserName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);

        boolean success = userDAO.addUser(user);

        HttpSession session = request.getSession();

        if (success) {
            // Auto-login the new user
            User loggedInUser = userDAO.loginUser(email, password);
            session.setAttribute("user", loggedInUser);

            // Merge pre-signup cart (if any)
            @SuppressWarnings("unchecked")
            List<Menu> guestCart = (List<Menu>) session.getAttribute("cart");
            if (guestCart != null && !guestCart.isEmpty()) {
                @SuppressWarnings("unchecked")
                List<Menu> userCart = (List<Menu>) session.getAttribute("cart");
                if (userCart == null) userCart = new ArrayList<>();

                for (Menu guestItem : guestCart) {
                    boolean found = false;
                    for (Menu userItem : userCart) {
                        if (userItem.getMenuId() == guestItem.getMenuId()) {
                            userItem.setQuantity(userItem.getQuantity() + guestItem.getQuantity());
                            found = true;
                            break;
                        }
                    }
                    if (!found) userCart.add(guestItem);
                }
                session.setAttribute("cart", userCart);
                session.removeAttribute("cart"); // clear guest cart
            }

            // Redirect to intended page if stored, else home
            String redirectAfterSignup = (String) session.getAttribute("loginRedirect");
            if (redirectAfterSignup != null) {
                session.removeAttribute("loginRedirect");
                response.sendRedirect(redirectAfterSignup);
            } else {
                response.sendRedirect(request.getContextPath() + "/home");
            }

        } else {
            response.sendRedirect(request.getContextPath() + "/signup?msg=error");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jspPages/signup.jsp").forward(request, response);
    }
}
