<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.bob.model.Menu, java.util.List, com.bob.model.User"%>

<%
    List<Menu> cart = (List<Menu>) session.getAttribute("cart");
    String contextPath = request.getContextPath();
    User user = (User) session.getAttribute("user");
    String msg = request.getParameter("msg");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Cart - MaxMeal</title>
    <link rel="stylesheet" href="<%= contextPath %>/css/cart.css?v=6">
    <style>
        .cart-message {
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
            font-weight: bold;
        }
        .cart-message.success { background-color: #d4edda; color: #155724; }
        .cart-message.error { background-color: #f8d7da; color: #721c24; }
        .cart-table td, .cart-table th { text-align: center; }
        .grand-total-value { font-weight: bold; }
    </style>
</head>
<body>

<header class="navbar">
    <div class="logo">MaxMeal</div>
    <nav class="nav-links">
        <a href="<%= contextPath %>/home">Home</a>
        <a href="<%= contextPath %>/restaurants">Restaurants</a>
        <% if(user != null) { %>
            <a href="<%= contextPath %>/cart">Cart</a>
            <a href="<%= contextPath %>/viewOrderHistory">Order History</a>
            <a href="<%= contextPath %>/logout">Logout</a>
        <% } else { %>
            <a href="<%= contextPath %>/login">Login</a>
            <a href="<%= contextPath %>/signup">Signup</a>
        <% } %>
    </nav>
</header>

<h1 class="cart-title">Your Cart</h1>

<%-- Display messages --%>
<% if(msg != null) { %>
    <div class="cart-message <%= "success_order".equals(msg) ? "success" : "error" %>">
        <% if("success_order".equals(msg)) { %>
            ✅ Order placed successfully!
        <% } else if("empty_cart".equals(msg)) { %>
            ⚠️ Your cart is empty.
        <% } else if("fail_order".equals(msg)) { %>
            ❌ Failed to place order. Please try again.
        <% } %>
    </div>
<% } %>

<% if (cart == null || cart.isEmpty()) { %>
    <p class="empty-cart">Your cart is empty!</p>
<% } else { %>

    <table class="cart-table">
        <tr>
            <th>Item</th>
            <th>Price (₹)</th>
            <th>Quantity</th>
            <th>Total (₹)</th>
            <th>Action</th>
        </tr>
        <%
            double grandTotal = 0;
            for(Menu item : cart){
                double total = item.getPrice() * item.getQuantity();
                grandTotal += total;
        %>
        <tr>
            <td><%= item.getItemName() %></td>
            <td>₹ <%= String.format("%.2f", item.getPrice()) %></td>
            <td><%= item.getQuantity() %></td>
            <td>₹ <%= String.format("%.2f", total) %></td>
            <td>
                <form action="<%= contextPath %>/removeFromCart" method="post">
                    <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                    <button type="submit" class="remove-btn">Remove</button>
                </form>
            </td>
        </tr>
        <% } %>
        <tr>
            <td colspan="3" class="grand-total-label">Grand Total:</td>
            <td colspan="2" class="grand-total-value">₹ <%= String.format("%.2f", grandTotal) %></td>
        </tr>
    </table>

    <% if(user != null) { %>
        <div class="place-order-container">
            <h3>Delivery Address</h3>
            <p><%= user.getAddress() %></p>

            <form action="<%= contextPath %>/placeOrder" method="post" class="place-order-form">
                <input type="hidden" name="address" value="<%= user.getAddress() %>">
                <select name="paymentMode" required>
                    <option value="">Select Payment Mode</option>
                    <option value="Cash">Cash</option>
                    <option value="UPI">UPI</option>
                </select>
                <button type="submit" class="place-order-btn">Place Order</button>
            </form>
        </div>
    <% } else { %>
        <p class="login-prompt">
            Please <a href="<%= contextPath %>/login">Login</a> to place your order.
        </p>
    <% } %>

<% } %>

</body>
</html>
