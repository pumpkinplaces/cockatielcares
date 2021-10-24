<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1">
<title>Change Password</title>
<link rel="shortcut icon" href="img/cockatiel.png" />
<link rel="stylesheet" href="css/stylesheet.css" type="text/css">
<script src="https://kit.fontawesome.com/9e36994fd1.js"></script>
</head>
<body>
	<%
	boolean noProblems = application.getAttribute("pwordProblem") == null;
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
		<form id="pfp" method="post" action="NewPass">
			<div class="elementList">
				<div class="row">
					<div class="textElem">
						<p>New Password</p>
					</div>
					<div class="inputElem">
						<%
						if (noProblems) {
						%>
						<input class="pfpBox" type="password" id="userEmail"
							name="newPassword">
						<%
						} else {
						%><input class="pfpBox" type="password"
							value="${applicationScope.newPassword}" id="userEmail"
							name="newPassword" spellcheck="false">
						<%
						}
						%>
					</div>
				</div>
				<div class="row">
					<div class="textElem">
						<p class="inside">Confirm new password</p>
					</div>
					<div class="inputElem">
						<%
						if (noProblems) {
						%>
						<input class="pfpBox" type="password" id="usermail"
							name="confirmedPassword">
						<%
						} else {
						%>
						<input class="pfpBox" type="password"
							value="${applicationScope.confirmedPassword}" id="usermail"
							name="confirmedPassword" spellcheck="false">
						<%
						}
						%>
					</div>
				</div>
			</div>
			<label id="showPass">Show password <input type="checkbox"
				onchange="showPasswd(this)" id="showPassBox"
				STYLE="margin-left: 4px; vertical-align: middle">
			</label>
			<p class="mistake" STYLE="text-align: center; font-size: 15px">${applicationScope.pwordProblem}</p>
			<input id="changeButton" type="submit" value="Change"> <a
				href="forgotPassword.jsp" class="lastelem" id="logout"> Forgot
				Password?</a>
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

	<script>
		function showPasswd(check) {
			var newPassword = document.getElementById("userEmail");
			var confirmedPassword = document.getElementById("usermail");
			if (check.checked) {
				newPassword.type = "text";
				confirmedPassword.type = "text";
			} else {
				newPassword.type = "password";
				confirmedPassword.type = "password";
			}
		}
	</script>
</body>
</html>
