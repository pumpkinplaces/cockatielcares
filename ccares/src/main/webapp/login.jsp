<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1">
<title>Your Account</title>
<link rel="shortcut icon" href="img/cockatiel.png" />
<link rel="stylesheet" href="css/stylesheet.css" type="text/css">
<script src="https://kit.fontawesome.com/9e36994fd1.js"></script>
</head>
<body>
	<%
	HttpSession session = request.getSession(false);
	if (session != null) {
		response.sendRedirect("makepfp.jsp");
		return;
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
			<li><a href="login.jsp" class="current">Login</a></li>
		</ul>
	</nav>


	<div class="afterHeader">
		<nav id="smallnav" class="small overContent">
			<ul class="small-ul amenu" id="smallList">
				<li><a href="index.jsp">Home</a></li>
				<li><a href="ask.jsp">Ask About It</a></li>
				<li><a href="trainingschedule.jsp">Training Schedule</a></li>
				<li><a href="aboutus.jsp">About Us</a></li>
				<li><a href="login.jsp" class="current">Login</a></li>
			</ul>
		</nav>
		<p class="writing">${newPassSuccess}</p>
		<form method="post" action="LoginCheck" id="loginSquare">
			<%
			if (application.getAttribute("loginUser") == null) {
			%>
			<input id="usernamelogin" class="ignoremargin" type="text"
				name="username" placeholder="Username" class="userpassTextBox"
				spellcheck="false"> <input id="passwordlogin"
				class="ignoremargin" type="password" name="password"
				placeholder="Password" class="userpassTextBox" spellcheck="false"
				onchange="showPasswd(this)">
			<%
			} else {
			%>
			<input id="usernamelogin" class="ignoremargin" type="text"
				name="username" placeholder="Username" class="userpassTextBox"
				spellcheck="false" value="${applicationScope.loginUser.username}">
			<input id="passwordlogin" class="ignoremargin" type="password"
				name="password" placeholder="Password" class="userpassTextBox"
				spellcheck="false" value="${applicationScope.loginUser.password}"
				onchange="showPasswd(this)">
			<%
			}
			%>

			<input id="loginbutton" type="submit" value="Login">
			<%
			if (application.getAttribute("loginError") != null) {
			%>
			<p class="mistake">${applicationScope.loginError}</p>
			<%
			}
			%>
			<a href="makeAcct.jsp" id="dontHaveAcct"> Make an account!</a> <label
				id="showPassLogin">Show password <input type="checkbox"
				onchange="showPasswd(this)" id="showPassBoxLogin"
				STYLE="margin-left: 4px; vertical-align: center;">
			</label> <label id="rememberme">Remember Me <input type="checkbox"
				STYLE="margin-left: 4px; vertical-align: center;" name="rememberMe"></label>
			<a href="ForgotPass?thevalue=forgotMyPass" id="forgotthepass">Forgot
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
	<script>
		function showPasswd(check) {
			var passwordlogin = document.getElementById("passwordlogin");
			if (check.checked) {
				passwordlogin.type = "text";
			} else {
				passwordlogin.type = "password";
			}
		}
	</script>
	<script src="menuanim.js"></script>

</body>
</html>