<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1">
<title>Successful Login</title>
<link rel="shortcut icon" href="img/cockatiel.png" />
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
		<h1 id="yourloggedin" class="centerit">You're logged in!</h1>
		<h1 id="yourusernameis" class="centerit givelotsofspace">Your
			username name is: ${user.username}</h1>
		<p class="centerit">Would you like to add a profile picture?</p>
		<form method="post" action="ProfilePic" enctype="multipart/form-data">
			<div id="choosepfp">
				<img src="img/nopfp.png" class="centerpic" id="nopfppic"> <input
					type="file" id="pfpfile" name="apfpfile"> <label
					for="pfpfile" id="uploadButton"> Choose photo</label>
			</div>
			<p class="mistake" STYLE="text-align: center">${pfpUploadError}</p>
			<p STYLE="text-align: center">Press "skip" if you do not wish to
				set a profile picture.</p>

			<input type="submit"
				class="centerpic saveAndContinue givelotsofspace"
				value="Save and Continue">
		</form>
		<a href="loginSuccessTraining.jsp"
			class="skip centerpic givelotsofspace"> Skip</a>
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
	<script src="pfp.js"></script>
</body>
</html>