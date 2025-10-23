<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Signup - MaxMeal</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/signup.css?v=4">

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
		
		<h2>Signup</h2>
		
		<%
   		 String msg = request.getParameter("msg");
			    if ("email_exists".equals(msg)) {
			%>
			    <div class="signup-error"> Email already exists!</div>
			<% } else if ("password_mismatch".equals(msg)) { %>
			    <div class="signup-error"> Password do not match!</div>
			<% } else if ("error".equals(msg)) { %>
			    <div class="signup-error"> Something went wrong. Please try again.</div>
			<% } %>
					
		
		
		
			<form action="<%= request.getContextPath() %>/signup" method="post">

			
				<label for="name">Name</label>
				<input type="text" id="name" name="name" required>
				
				<label for="email">Email</label>
				<input type="email" id="email" name="email" required>
				
				<label for="phone">Phone</label>
				<input type="tel" id="phone" name="phone" required>
				
				<label for="password">Password</label>
				<input type="password" id="password" name="password" required>
				
				<label for="confirmpassword">Confirm Password</label>
				<input type="password" id="confirmpassword" name="confirmpassword" required>
				
				<button type="submit">Signup</button>
			
			</form>
	<p class="switch-link">Already have an account?<a href="<%= request.getContextPath() %>/login">Login</a></p>
	
	</div>

</div>
		



</body>
</html>