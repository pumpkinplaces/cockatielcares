<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.ccares.email.EmailUtility, com.ccares.userinfo.CcaresUser"
	session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Confirm Email Address</title>
<link rel="shortcut icon" href="img/cockatiel.png" />
<link rel="stylesheet" href="css/stylesheet.css" type="text/css">
<script src="https://kit.fontawesome.com/9e36994fd1.js"></script>
</head>
<body>
	<%
	if (application.getAttribute("passwordProcess") == null || application.getAttribute("usernameExists") == null) {
		response.sendRedirect("login.jsp");
		return;
	}
	application.removeAttribute("passwordProcess");
	application.removeAttribute("usernameExists");
	String coveredEmail = "";
	CcaresUser user = (CcaresUser) application.getAttribute("passworduser");
	if (user != null) {
		String email = user.getEmail();
		int atIndex = email.indexOf("@");
		String emailEnd = email.substring(atIndex);
		String first2 = email.substring(0, 2);
		String fiveAsterisk = "*****";
		coveredEmail = first2 + fiveAsterisk + emailEnd;
	}
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

		<h1 class="writing topElem" id="whatsYourEmail">
			We will send a verification code to
			<%=coveredEmail%>. Please enter the full email address below for
			confirmation.
		</h1>
		<form method="post" action="EmailSender" id="makeEmail">
			<label for="emailaddr" id="verifyCode">Email Address</label>
			<nav>
				<ul class="boxSubmitList">
					<li>
						<%
						if (application.getAttribute("emailConfirmationError") == null) {
						%> <input type="text" name="uncoveredemail" class="inputBoxVerify"
						STYLE="margin-bottom: 20px"> <%
 } else {
 %> <input
						type="text" name="uncoveredemail" class="inputBoxVerify"
						STYLE="margin-bottom: 20px" value="${applicationScope.sentEmail}">
						<%
						}
						%>
					</li>
					<li><input type="submit" class="saveAndContinue"
						value="Submit" id="verifyButton"></li>
				</ul>
			</nav>
			<p class="mistake bottommargin">${applicationScope.emailConfirmationError}</p>
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
	application.removeAttribute("emailConfirmatinError");
	%>
</body>
</html>