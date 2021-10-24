<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1">
<title>Training Schedule</title>
<link rel="shortcut icon" href="img/cockatiel.png" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	
</script>
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
			<li><a href="trainingschedule.jsp" class="current">Training
					Schedule</a></li>
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
				<li><a href="index.jsp">Home</a></li>
				<li><a href="ask.jsp">Ask About It</a></li>
				<li><a href="trainingschedule.jsp" class="current">Training
						Schedule</a></li>
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
		<div class="scheduleStuff">
			<div class="calendarContainer">
				<div class="calendar">
					<div class="month">
						<i class="fas fa-arrow-circle-left prev hoverEnlarge"></i>
						<div class="date">
							<h1></h1>
							<p></p>
						</div>
						<i class="fas fa-arrow-circle-right next hoverEnlarge"></i>
					</div>
					<div class="weekdays">
						<div>Sun</div>
						<div>Mon</div>
						<div>Tue</div>
						<div>Wed</div>
						<div>Thu</div>
						<div>Fri</div>
						<div>Sat</div>
					</div>
					<div class="days" id="alldays"></div>
				</div>
			</div>
			<div class="scheduleContainer">
				<div class="schedule">
					<div id="theDay">Today</div>
					<form id="scheduleForm">
						<div contentEditable = "true" id="remSchedule">
							
						</div>
					</form>
				

				</div>
			</div>
		</div>
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
	<script src="calendar.js"></script>
</body>

</html>