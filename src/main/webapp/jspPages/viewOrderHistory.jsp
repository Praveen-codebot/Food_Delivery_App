<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.bob.model.Orders, com.bob.model.OrderItem"%>

<%
    List<Orders> orderList = (List<Orders>) request.getAttribute("orderList");
    String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order History - MaxMeal</title>
    <link rel="stylesheet" href="<%= contextPath %>/css/viewOrderHistory.css?v=10">
</head>
<body>

<header class="navbar">
    <div class="logo">MaxMeal</div>
    <nav class="nav-links">
        <a href="<%= contextPath %>/restaurants">Restaurants</a>
        <a href="<%= contextPath %>/cart">Cart</a>
        <a href="<%= contextPath %>/viewOrderHistory">Order History</a>
        <a href="<%= contextPath %>/logout">Logout</a>
    </nav>
</header>

<div class="order-history-container">
    <h1>Your Order History</h1>

    <% if(orderList == null || orderList.isEmpty()) { %>
        <p class="empty-message">You have no past orders.</p>
    <% } else { %>
        <% for(Orders order : orderList) { %>
            <div class="order-card">
                <div class="order-header">
                    <h2>Order #<%= order.getOrderId() %></h2>
                    <span class="order-status"><%= order.getStatus() %></span>
                </div>
                <p class="order-date">Date: <%= order.getOrderDate() %></p>
                <p class="order-total">Total: ₹<%= order.getTotalAmount() %></p>
                <span>--------Items--------</span><br/>

                <div class="order-items-list">
                    <% for(OrderItem item : order.getOrderItems()) { %>
                        <div class="order-item">
                            <!--  -->
                            <div class="item-info">
                                <h4><%= item.getMenuName() %></h4>
                                <p>Price: ₹<%= item.getTotalPrice() / item.getQuantity() %></p>
                            </div>
                            <div class="item-quantity">
                                <span> Quantity: <%= item.getQuantity() %> </span>
                            </div>
                        </div>
                    <% } %>
                </div>
            </div>
        <% } %>
    <% } %>
</div>

</body>
</html>
