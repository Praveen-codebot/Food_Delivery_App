<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login - MaxMeal</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/login.css?v=6">

</head>
<body>


	<!-- NAVBAR -->
	<header class="navbar">
	
	<div class="logo">MaxMeal</div>
	
		<nav class="nav-links">
		
			<a href="<%= request.getContextPath() %>/home">Home</a>
			<a href="<%= request.getContextPath() %>/restaurants">Restaurants</a>
			<a href="<%= request.getContextPath() %>/login">Login</a>
			<a href="<%= request.getContextPath() %>/signup">Signup</a>
			
	     </nav>
	</header>
	


	<div class="auth-container">
		<div class="auth-box">
			<h2>Login</h2>

			
		
			<form action="<%= request.getContextPath() %>/login" method="post">

			
				<label for="email">Email</label>
				<input type="email" id="email" name="email" required>
				
				<label for="password">Password</label> 	
				<input type="password" id="password" name="password" required>
				
					<%
			    String msg = request.getParameter("msg");
			    if("invalid".equals(msg)) {
			%>
			    <div class="login-error">
			         Wrong email or password!
			    </div>
			<% } %>
				
				<div class="remember-me">
				
					<input type="checkbox" id="remember" name="remember">
					<label for="remember">Remember Me</label>
				
				</div>
				
				<button type="submit">Login</button>
			
			</form>
		<p class="switch-link">New Here?<a href="<%= request.getContextPath() %>/signup">Signup</a></p>
		
		</div>
	
	</div>






</body>
</html>