<%@ page import="com.bob.model.User" %>
<%
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Address - MaxMeal</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/addAddress.css?v=1">
</head>
<body>
    <div class="add-address-container">
        <h2>Add Your Delivery Address</h2>
        <form action="<%= request.getContextPath() %>/addAddress" method="post">
            <textarea name="address" placeholder="Enter your delivery address" required></textarea>
            <button type="submit">Save Address</button>
        </form>
        <% if(request.getAttribute("error") != null) { %>
            <p class="error"><%= request.getAttribute("error") %></p>
        <% } %>
    </div>
</body>
</html>
