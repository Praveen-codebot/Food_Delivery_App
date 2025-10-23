<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.bob.model.User, com.bob.model.Restaurant, com.bob.model.Menu"%>

<%
    User user = (User) session.getAttribute("user");
    List<Restaurant> restaurants = (List<Restaurant>) request.getAttribute("restaurants");
    List<Menu> popularMenus = (List<Menu>) request.getAttribute("popularMenus");
    List<Menu> guestCart = (List<Menu>) session.getAttribute("cart");
    String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home - MaxMeal</title>
    <link rel="stylesheet" href="<%= contextPath %>/css/home.css?v=4">
</head>
<body>

<!-- Navbar -->
<header class="navbar">
    <div class="logo"><span class="logo-text">MaxMeal</span></div>
    <nav class="nav-links">
        <a href="<%= contextPath %>/home">Home</a>
        <a href="<%= contextPath %>/restaurants">Restaurants</a>

        <% if (user != null) { %>
            <a href="<%= contextPath %>/cart">Cart</a>
            <a href="<%= contextPath %>/viewOrderHistory">Order History</a>
            <a href="<%= contextPath %>/logout">Logout</a>
        <% } else { %>
            <a href="<%= contextPath %>/login">Login</a>
            <a href="<%= contextPath %>/signup">Signup</a>
        <% } %>
    </nav>
</header>

<!-- Hero -->
<section class="hero">
    <div class="hero-content">
        <% if (user == null) { %>
            <h1><span class="highlight-text">Welcome to MaxMeal!</span></h1>
            <p>Login to Order Some Food</p>
            <a href="<%= contextPath %>/login" class="cta-btn">Login</a><br/><br/>
        <% } else { %>
            <h1>Welcome back, <%= (user.getUserName() != null ? user.getUserName() : "User") %>!</h1>
            <p>Check out popular restaurants and menu items...</p>
        <% } %>
    </div>
</section>

<!-- Restaurants Section -->
<section class="restaurants-section">
    <h2>Top Restaurants</h2>
    <div class="restaurants-container">
        <% if (restaurants != null && !restaurants.isEmpty()) { 
               for (Restaurant r : restaurants) { 
                   String imagePath = (r.getImagePath() != null && !r.getImagePath().isEmpty()) 
                        ? contextPath + "/" + r.getImagePath() 
                        : contextPath + "/images/default-restaurant.jpg";
        %>
            <div class="restaurant-card">
                <img src="<%= imagePath %>" 
                     alt="<%= r.getName() != null ? r.getName() : "Restaurant" %>">
                <div class="info">
                    <h3><%= r.getName() != null ? r.getName() : "Unknown Restaurant" %></h3>
                    <p><%= r.getAddress() != null ? r.getAddress() : "Address not available" %></p>
                    <p>Rating: <%= r.getRating() %> | ETA: <%= r.getEta() %> mins</p>
                    <a href="<%= contextPath %>/menu?restaurantId=<%= r.getRestaurantId() %>" class="menu-btn">View Menu</a>
                </div>
            </div>
        <% } 
           } else { %>
            <p>No restaurants available.</p>
        <% } %>
    </div>
</section>

<!-- Popular Menu Items -->
<% if (popularMenus != null && !popularMenus.isEmpty()) { %>
    <h2>Popular Menu Items</h2>
    <div class="menu-container">
        <% for (Menu m : popularMenus) { 
               String menuImage = (m.getImagePath() != null && !m.getImagePath().isEmpty()) 
                        ? contextPath + "/" + m.getImagePath() 
                        : contextPath + "/images/default-food.jpg";
        %>
            <div class="menu-card">
                <img src="<%= menuImage %>" 
                     alt="<%= m.getItemName() != null ? m.getItemName() : "Menu Item" %>">
                <div class="info">
                    <h4><%= m.getItemName() != null ? m.getItemName() : "Unnamed Item" %></h4>
                    <p>Price: ₹<%= m.getPrice() %></p>

                    <form action="<%= contextPath %>/addToCart" method="post" class="cart-form">
                        <input type="hidden" name="menuId" value="<%= m.getMenuId() %>">
                        <input type="hidden" name="quantity" value="1">
                        <button type="submit" class="add-cart-btn">
                            <% if (user != null) { %> Add to Cart <% } else { %> Add to Cart (Login later) <% } %>
                        </button>
                    </form>
                </div>
            </div>
        <% } %>
    </div>
<% } %>

</body>
</html>
