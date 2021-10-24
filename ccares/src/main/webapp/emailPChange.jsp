<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.ccares.email.EmailUtility,com.ccares.userinfo.CcaresUser"
	session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Verify Email Address</title>
<link rel="shortcut icon" href="img/cockatiel.png" />
<link rel="stylesheet" href="css/stylesheet.css" type="text/css">
<script src="https://kit.fontawesome.com/9e36994fd1.js"></script>
</head>
<body>
	<%
	if (application.getAttribute("passwordProcess") == null) {
		response.sendRedirect("login.jsp");
		return;
	}
	application.removeAttribute("passwordProcess");
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
			<li><a href="login.jsp">Login</a></li>
		</ul>
	</nav>

	<div class="afterHeader">
		<nav id="smallnav" class="small overContent">
			<ul class="small-ul amenu" id="smallList">
				<li><a href="index.jsp">Home</a></li>
				<li><a href="ask.jsp">Ask About It</a></li>
				<li><a href="trainingschedule.jsp">Training Schedule</a></li>
				<li><a href="aboutus.jsp">About Us</a></li>
				<li><a href="login.jsp">Login</a></li>
			</ul>
		</nav>

		<h1 class="writing topElem" id="whatsYourEmail">A verification
			code has been sent to ${applicationScope.passworduser.email}. Please
			enter that code below.</h1>
		<form method="post" action="emailVerifier" id="makeEmail">
			<label for="emailaddr" id="verifyCode">Verification Code</label>
			<nav>
				<ul class="boxSubmitList">
					<li><input type="text" name="replyCode" class="inputBoxVerify"
						STYLE="margin-bottom: 20px"></li>
					<li><input type="submit" class="saveAndContinue"
						value="Submit" id="verifyButton"></li>
				</ul>
			</nav>
			<p class="mistake bottommargin">${applicationScope.wrongVerCode}</p>
		</form>
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
	<%
	application.removeAttribute("wrongVerCode");
	%>
</body>
</html>