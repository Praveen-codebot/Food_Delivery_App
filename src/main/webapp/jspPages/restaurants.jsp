<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.bob.model.Restaurant, com.bob.model.User, java.util.List" %>

<%
    List<Restaurant> restaurants = (List<Restaurant>) request.getAttribute("restaurants");
    String search = (String) request.getAttribute("search");
    User user = (User) session.getAttribute("user");
    String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%= contextPath %>/">
    <meta charset="UTF-8">
    <title>Restaurants - MaxMeal</title>
    <link rel="stylesheet" href="<%= contextPath %>/css/restaurants.css?v=5">
</head>
<body>

<!-- Navbar -->
<header class="navbar">
    <div class="logo">MaxMeal</div>
    <nav class="nav-links">
        <a href="<%= contextPath %>/home">Home</a>
        <a href="<%= contextPath %>/restaurants">Restaurants</a>

        <% if (user != null) { %>
            <a href="<%= contextPath %>/cart">Cart</a>
            <a href="<%= contextPath %>/viewOrderHistory">Order History</a>
            <a href="<%= contextPath %>/logout">Logout</a>
        <% } else { %>
            <a href="<%= contextPath %>/cart">Cart</a>
            <a href="<%= contextPath %>/login">Login</a>
            <a href="<%= contextPath %>/signup">Signup</a>
        <% } %>
    </nav>
</header>

<!-- Restaurant List -->
<section class="restaurant-list">
    <h1>Restaurants Near You</h1>

    <!-- Search Form -->
    <form action="<%= contextPath %>/restaurants" method="get" class="search-form">
        <input type="text" name="search" placeholder="Search Restaurants..." 
               value="<%= (search != null) ? search : "" %>">
        <button type="submit">Search</button>
    </form>

    <div class="card-container">
        <% if (restaurants != null && !restaurants.isEmpty()) {
               for (Restaurant r : restaurants) { %>
            <div class="card">
                <a href="<%= contextPath %>/menu?restaurantId=<%= r.getRestaurantId() %>">
                    <img src="<%= contextPath + "/" + (r.getImagePath() != null ? r.getImagePath() : "images/default-restaurant.jpg") %>" 
                         alt="<%= (r.getName() != null ? r.getName() : "Restaurant") %>" 
                         class="restaurant-image">
                    <div class="info">
                        <h2><%= (r.getName() != null ? r.getName() : "Unknown Restaurant") %></h2>
                        <p><%= (r.getAddress() != null ? r.getAddress() : "Address not available") %></p>
                    </div>
                </a>
            </div>
        <% } 
           } else { %>
            <p style="text-align:center; margin-top:20px;">No restaurants found.</p>
        <% } %>
    </div>
</section>

</body>
</html>
