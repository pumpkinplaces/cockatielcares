<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="img/cockatiel.png" />
<title>About Us</title>
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
			<li><a href="aboutus.jsp" class="current">About Us</a></li>
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


	<nav id="smallnav" class="small overContent">
		<ul class="small-ul amenu" id="smallList">
			<li><a href="index.jsp">Home</a></li>
			<li><a href="ask.jsp">Ask About It</a></li>
			<li><a href="trainingschedule.jsp">Training Schedule</a></li>
			<li><a href="aboutus.jsp" class="current">About Us</a></li>
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
		<h1 class="writing">About Us</h1>
		<p class="writing">Cockatiel Cares provides helpful information
			about owning a pet. We provide people with both the knowledge of how
			to prepare for getting a pet as well as how to best keep them happy,
			healthy, and active afterwards! We simultaneously also provide an
			online schedule where users can maintain an organized log of things
			that they want to do with their pets. This calendar can include
			training schedules, feeding schedules, walking schedules, or
			pet-shopping schedules! As long as you have a way to focus some of
			your attention specifically to your pet, relationships between pets
			and their owners can flourish and improve! As pet owners, we should
			do our best to prioritize our pets health and happiness!</p>
		<p class="writing">Lastly, if users have any questions or concerns
			about their pet, they can ask on our "Ask About It" section where other
			pet lovers can share their knowledge and insight regarding pet care.</p>
		<p class="writing">Cockatiel Cares was developed by Amanda Vialva.
		</p>

	</div>
	<footer id="footer">
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