<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.ccares.userinfo.CcaresUser,com.ccares.database.LoginDb,com.ccares.userinfo.CcaresUser,java.util.Objects"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1">
<title>Your Profile</title>
<link rel="shortcut icon" href="img/cockatiel.png" />
<link rel="stylesheet" href="css/stylesheet.css" type="text/css">
<script src="https://kit.fontawesome.com/9e36994fd1.js"></script>
</head>
<body>
	<%
	LoginDb ldb = new LoginDb();
	if (session.isNew()) {
		session.invalidate();
		response.sendRedirect("login.jsp");
		return;
	} else {
		if (session.getAttribute("user") != null) {
			CcaresUser user = (CcaresUser) session.getAttribute("user");
			String username = user.getUsername();
			String email = user.getEmail();
		} else {
			session.invalidate();
			response.sendRedirect("login.jsp");
			return;
		}
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
			<li><a href="makepfp.jsp" class="current">My Account</a></li>
		</ul>
	</nav>

	<div class="afterHeader">
		<nav id="smallnav" class="small overContent">
			<ul class="small-ul amenu" id="smallList">
				<li><a href="index.jsp">Home</a></li>
				<li><a href="ask.jsp">Ask About It</a></li>
				<li><a href="trainingschedule.jsp">Training Schedule</a></li>
				<li><a href="aboutus.jsp">About Us</a></li>
				<li><a href="makepfp.jsp" class="current">My Account</a></li>
			</ul>
		</nav>

		<form id="pfp" method="post" action="SaveUserInfo"
			enctype="multipart/form-data">
			<div class="container" id="pfpContainer">
				<%
				CcaresUser user = (CcaresUser) session.getAttribute("user");
				String profilePic = user.getProfilePic();
				if (profilePic == null || profilePic == "") {
				%>
				<img src="img/nopfp.png" id="profilePic" />
				<%
				} else {
				%>
				<img src="data:image/jpg;base64,${user.profilePic}" id="profilePic" />
				<%
				}
				%>

				<input class="centered" id="pfpText" value="Change profile picture"
					type="file" name="newPfp" accept=".png, .jpg, .jpeg"> <label
					class="centered" class="centered" id="pfpTextLabel" for="pfpText">Change
					profile picture</label>

			</div>
			<div class="elementList">
				<div class="row">
					<div class="textElem">
						<p>Username</p>
					</div>
					<div class="inputElem">
						<%
						if (session.getAttribute("errormessage") == null) {
						%>
						<input class="pfpBox" type="text" id="userUname"
							value="${user.username}" name="username">
						<%
						} else {
						%>
						<input class="pfpBox" type="text" id="userUname"
							value="${fakeuser.username}" name="username" STYLE="color: red">
					</div>
					<div class="textElem"></div>
					<div class="pfpError">
						<p class="mistake">${errormessage}</p>
						<%
						}
						%>
					</div>
				</div>
				<div class="row">
					<div class="textElem">
						<p>Password</p>
					</div>
					<div class="inputElem">
						<a href="changePass.jsp" id="changeYourPassword">Change your
							password</a>
					</div>
				</div>
				<div class="row">
					<div class="textElem">
						<p>Email Address</p>
					</div>
					<div class="inputElem">
						<%
						if (session.getAttribute("emailerror") == null) {
						%>
						<input type="text" value="${user.email}" id="userEmail"
							name="email">
						<%
						} else {
						%>
						<input type="text" value="${fakeuser.email}" id="userEmail"
							name="email" STYLE="color: red">

					</div>
					<div class="textElem"></div>
					<div class="pfpError">
						<p class="mistake">${emailerror}</p>
						<%
						}
						%>
					</div>
				</div>
			</div>
			<input id="saveButton" type="submit" value="Save" disabled> <a
				href="Logout" class="lastelem" id="logout"> Log Out</a>
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
		const saveButton = document.getElementById("saveButton");
		const pfpContainer = document.getElementById("pfpContainer");
		const pfpText = document.getElementById("pfpTextLabel");
		const file = document.querySelector('#pfpText');
		const img = document.querySelector('#profilePic');
		const unameTF = document.querySelector('#userUname');
		const mailTF = document.querySelector('#userEmail');

		unameTF.addEventListener("keyup", darkenSave);
		mailTF.addEventListener("keyup", darkenSave);

		pfpContainer.addEventListener("mouseenter", function(event) {
			pfpText.style.animation = "pfpTextFadeIn 0.5s forwards";
		});
		pfpContainer.addEventListener("mouseleave", function(event) {
			pfpText.style.animation = "pfpTextFadeOut 0.5s forwards";
		});

		function darkenSave(e) {
			saveButton.style.opacity = 1;
			saveButton.style.cursor = "pointer";
			saveButton.disabled = false;
		}

		file.addEventListener('change', function() {
			const selectedFile = this.files[0];
			const reader = new FileReader();
			reader.addEventListener('load', function() {
				img.setAttribute('src', reader.result);
			});
			reader.readAsDataURL(selectedFile);
			saveButton.style.opacity = 1;
			saveButton.style.cursor = "pointer";
			saveButton.disabled = false;
		});

		var pfpMediaQ = window.matchMedia('(max-width : 750px)');
		pfpMediaQ.addEventListener('change', showChangePfp);

		function showChangePfp(e) {
			if (e.matches) {
				pfpText.style.animation = "";
				pfpText.style.opacity = "1";
			} else {
				pfpText.style.opacity = "0";
			}
		}
	</script>
	<script src="menuanim.js"></script>
	<%
	session.removeAttribute("errormessage");
	session.removeAttribute("emailerror");
	%>
</body>
</html>