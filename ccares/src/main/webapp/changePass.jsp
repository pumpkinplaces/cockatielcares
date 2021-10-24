<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="img/cockatiel.png" />
<title>Change Password</title>
<link rel="stylesheet" href="css/stylesheet.css" type="text/css">
<script src="https://kit.fontawesome.com/9e36994fd1.js"></script>
</head>
<body>
	<%
	if (session.isNew()) {
		session.invalidate();
		response.sendRedirect("login.jsp");
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
			<li><a href="makepfp.jsp">My Account</a></li>
		</ul>
	</nav>

	<div class="afterHeader">
		<nav id="smallnav" class="small overContent">
			<ul class="small-ul amenu" id="smallList">
				<li><a href="index.jsp">Home</a></li>
				<li><a href="ask.jsp">Ask About It</a></li>
				<li><a href="trainingschedule.jsp">Training Schedule</a></li>
				<li><a href="aboutus.jsp">About Us</a></li>
				<li><a href="makepfp.jsp">My Account</a></li>
			</ul>
		</nav>
		<form id="pfp" method="post" action="ChangePass">
			<div class="elementList">
				<div class="row">
					<div class="textElem">
						<p>Current Password</p>
					</div>
					<div class="inputElem">
						<%
						boolean noProblems = session.getAttribute("aPasswordError") == null;
						if (noProblems) {
						%>
						<input class="pfpBox" type="text" id="userUname" name="oldPass">
						<%} else {%>
						<input class="pfpBox" type="text" id="userUname"
							value="${oldPassword}" name="oldPass">
						<%
						}
						%>
						<p class="mistake changePassError">${oldPerror}</p>
					</div>
				</div>
				<div class="row">
					<div class="textElem">
						<p>New Password</p>
					</div>
					<div class="inputElem">
						<%
						if (noProblems) {
						%>
						<input class="pfpBox" type="password" id="userEmail"
							name="newPass">
						<%
						} else {
						%><input class="pfpBox" type="password" value="${newPassword}"
							id="userEmail" name="newPass">
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
							name="confirmedPass">
						<%
						} else {
						%>
						<input class="pfpBox" type="password" value="${confirmedPass}"
							id="usermail" name="confirmedPass">
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
			<p class="mistake" STYLE="text-align: center; font-size: 15px">${newPerror}</p>
			<input id="changeButton" type="submit" value="Change">
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
	<%
	session.removeAttribute("newPerror");
	session.removeAttribute("aPasswordError");
	session.removeAttribute("oldPerror");
	%>
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
