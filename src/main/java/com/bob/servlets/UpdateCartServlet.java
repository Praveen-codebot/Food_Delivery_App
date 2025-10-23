package com.bob.servlets;

import com.bob.model.Menu;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("serial")
@WebServlet("/updateCart")
public class UpdateCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String menuIdParam = request.getParameter("menuId");
        String action = request.getParameter("action");

        if(menuIdParam == null || action == null){
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        HttpSession session = request.getSession();
        List<Menu> cart = (List<Menu>) session.getAttribute("cart");

        if(cart != null){
            int menuId = Integer.parseInt(menuIdParam);

            for(Menu item : cart){
                if(item.getMenuId() == menuId){
                    if("increase".equals(action)){
                        item.setQuantity(item.getQuantity() + 1);
                    } else if("decrease".equals(action) && item.getQuantity() > 1){
                        item.setQuantity(item.getQuantity() - 1);
                    }
                    break;
                }
            }
            session.setAttribute("cart", cart);
        }

        response.sendRedirect(request.getContextPath() + "/cart");
    }
}
