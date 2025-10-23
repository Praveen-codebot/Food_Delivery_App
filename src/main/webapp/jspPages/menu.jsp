<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.bob.model.Menu, com.bob.model.User, java.util.List"%>

<%
    List<Menu> menuItems = (List<Menu>) request.getAttribute("menuItems");
    String message = (String) request.getAttribute("message");
    String contextPath = request.getContextPath();
    User user = (User) session.getAttribute("user");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu - MaxMeal</title>
<link rel="stylesheet" href="<%= contextPath %>/css/menu.css?v=4">
</head>
<body>

<!-- Navbar -->
<header class="navbar">
    <div class="logo">MaxMeal</div>
    <nav class="nav-links">
        <a href="<%= contextPath %>/home">Home</a>
        <a href="<%= contextPath %>/restaurants">Restaurants</a>
        <a href="<%= contextPath %>/cart">Cart</a>
        <% if (user != null) { %>
            <a href="<%= contextPath %>/viewOrderHistory">Order History</a>
            <a href="<%= contextPath %>/logout">Logout</a>
        <% } else { %>
            <a href="<%= contextPath %>/login">Login</a>
            <a href="<%= contextPath %>/signup">Signup</a>
        <% } %>
    </nav>
</header>

<h1>Menu Items</h1>

<div class="menu-items">
    <% if (menuItems != null && !menuItems.isEmpty()) { %>
        <div class="card-container">
            <% for (Menu m : menuItems) { 
                   String imgSrc = (m.getImagePath() != null && !m.getImagePath().isEmpty()) 
                                    ? contextPath + "/" + m.getImagePath() 
                                    : contextPath + "/images/default-menu.jpg";
            %>
                <div class="card">
                    <img src="<%= imgSrc %>" 
                         alt="<%= m.getItemName() != null ? m.getItemName() : "Menu Item" %>" 
                         class="menu-image">

                    <h2><%= m.getItemName() != null ? m.getItemName() : "Unknown Item" %></h2>
                    <p>Price: ₹ <%= m.getPrice() %></p>

                    <!-- Add to cart form -->
                    <form action="<%= contextPath %>/addToCart" method="post" class="cart-form">
                        <input type="hidden" name="menuId" value="<%= m.getMenuId() %>">
                        <input type="hidden" name="quantity" value="1" class="hidden-qty">

                        <div class="quantity-container">
                            <button type="button" class="qty-btn minus" value="decrease">-</button>
                            <input type="text" value="1" class="qty-input" readonly>
                            <button type="button" class="qty-btn plus" value="increase">+</button>
                        </div>

                        <button type="submit" class="add-cart-btn">
                            Add to Cart
                        </button>
                    </form>
                </div>
            <% } %>
        </div>
    <% } else { %>
        <p style="text-align:center; margin-top:20px;">
            <%= message != null ? message : "No menu items found." %>
        </p>
    <% } %>
</div>

<!-- Quantity Buttons Script -->
<script>
document.querySelectorAll('.card').forEach(card => {
    const minusBtn = card.querySelector('.minus');
    const plusBtn = card.querySelector('.plus');
    const qtyInput = card.querySelector('.qty-input');
    const hiddenInput = card.querySelector('.hidden-qty');

    minusBtn.addEventListener('click', () => {
        let current = parseInt(qtyInput.value);
        if (current > 1) {
            current -= 1;
            qtyInput.value = current;
            hiddenInput.value = current;
        }
    });

    plusBtn.addEventListener('click', () => {
        let current = parseInt(qtyInput.value);
        current += 1;
        qtyInput.value = current;
        hiddenInput.value = current;
    });
});
</script>

</body>
</html>
