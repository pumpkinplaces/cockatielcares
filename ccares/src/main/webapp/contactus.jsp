<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1">
<title>Cockatiel Cares</title>
<link rel="shortcut icon" href="img/cockatiel.png" />
<link rel="stylesheet" href="css/stylesheet.css" type="text/css">
<script src="https://kit.fontawesome.com/9e36994fd1.js"></script>
</head>
<body>
	<%
	HttpSession session = request.getSession(false);
	%>
	<div id="darkcover"></div>
	<div id="strip1" class="overContent">
		<a href="index.jsp" class="logolink"> <img
			src="img/cockatielcareslogo.png" alt="logo" class="logo">
		</a>
		<div class="menulines" id="smallmenu">
			<div id="line1"></div>
			<div id="line2"></div>
		</div>

	</div>

	<nav class="big overContent">
		<ul class="amenu" id="headerUL">
			<li><a href="index.jsp">Home</a></li>
			<li><a href="ask.jsp">Ask About It</a></li>
			<li><a href="trainingschedule.jsp">Training Schedule</a></li>
			<li><a href="aboutus.jsp">About Us</a></li>
			<%
			if (session != null) {
			%>
			<li><a href="makepfp.jsp">My Account</a></li>
			<%
			} else {
			%>
			<li><a href="login.jsp">Login</a></li>
			<%
			}
			%>
		</ul>
	</nav>


	<div class="afterHeader">
		<nav id="smallnav" class="small overContent">
			<ul class="small-ul amenu" id="smallList">
				<li><a href="index.jsp" class="current">Home</a></li>
				<li><a href="ask.jsp">Ask About It</a></li>
				<li><a href="trainingschedule.jsp">Training Schedule</a></li>
				<li><a href="aboutus.jsp">About Us</a></li>
				<%
				if (session != null) {
				%>
				<li><a href="makepfp.jsp">My Account</a></li>
				<%
				} else {
				%>
				<li><a href="login.jsp">Login</a></li>
				<%
				}
				%>
			</ul>
		</nav>
		<h1 class="writing">Contact Us</h1>
		<p class="writing">
			Please email us at <a href="mailto:help@cockatielcares.com"
				STYLE="color: rgb(50, 82, 168); font-size: unset;">help@cockatielcares.com</a>
			if you have any questions or concerns about our website or about one
			of your pets!
		</p>
		<p class = "writing">
			Please email <a href="mailto:help@cockatielcares.com"
				STYLE="color: rgb(50, 82, 168); font-size: unset;">amandavialva@cockatielcares.com</a>
			if you have any business inquiries!
		</p>
	</div>

	<footer id="footer" class="notMuchOnPage">
		<div id="emailAddress">Cockatiel Cares</div>
		<div id="socialMedia">
			<ul id="socialmedia">
				<li><a class="fab fa-facebook-f"
					href="https://www.facebook.com/cee.cares.3" target="_blank"
					rel="noopener noreferrer"></a></li>
				<li><a class="fab fa-instagram"
					href="https://www.instagram.com/cockatielcares/" target="_blank"
					rel="noopener noreferrer"></a></li>
			</ul>
		</div>
		<div id="contact" class="footer-elem"
			onclick="location.href='contactus.jsp'">Contact Us</div>
	</footer>
	<script src="menuanim.js"></script>
</body>
</html>