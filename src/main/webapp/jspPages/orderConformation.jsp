<%@ page import="com.bob.model.Orders, com.bob.model.OrderItem, com.bob.model.Restaurant, java.util.List" %>
<%
    Orders order = (Orders) request.getAttribute("order");
    List<OrderItem> items = (List<OrderItem>) request.getAttribute("orderItems");
    Restaurant restaurant = (Restaurant) request.getAttribute("restaurant");
    String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Confirmation</title>
<link rel="stylesheet" href="<%= contextPath %>/css/orderConfirmation.css?v=1">
</head>
<body>

<h1> Order Placed Successfully!</h1>
<p>Order ID: <%= order.getOrderId() %></p>
<p>Restaurant: <%= restaurant != null ? restaurant.getName() : "Unknown" %></p>
<p>Payment Mode: <%= order.getPaymentMode() %></p>
<p>Status: <%= order.getStatus() %></p>

<h2>Items Ordered</h2>
<table>
    <tr>
        <th>Item</th><th>Quantity</th><th>Price</th><th>Total</th>
    </tr>
    <%
        double grandTotal = 0;
        for(OrderItem item : items){
            grandTotal += item.getTotalPrice();
    %>
    <tr>
        <td><%= item.getItemName() %></td>
        <td><%= item.getQuantity() %></td>
        <td>₹ <%= String.format("%.2f", item.getTotalPrice()/item.getQuantity()) %></td>
        <td>₹ <%= String.format("%.2f", item.getTotalPrice()) %></td>
    </tr>
    <% } %>
    <tr>
        <td colspan="3">Grand Total</td>
        <td>₹ <%= String.format("%.2f", grandTotal) %></td>
    </tr>
</table>

<a href="<%= contextPath %>/home">Back to Home</a>
</body>
</html>
