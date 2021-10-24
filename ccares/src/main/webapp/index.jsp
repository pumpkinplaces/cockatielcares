<%@ page language="java" contentType="text/html; charset =UTF-8"
	pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" href="img/cockatiel.png" />
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1">
<title>Cockatiel Cares</title>
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

		<h1 class="writing">Welcome to Cockatiel Cares!</h1>
		<p class="writing">
			If you <a href="#haveAPet"
				STYLE="color: rgb(50, 82, 168); font-size: unset">have a pet</a>, <a
				href="#wantAPet" STYLE="color: rgb(50, 82, 168); font-size: unset">are
				interested in getting a pet</a>, or if you're simply a pet lover, you've
			come to the right place! Here, you can learn all about your pet's
			needs by asking questions, reading other people's questions, or even
			by providing helpful responses!
		</p>
		<p class="writing">As pet owners, it is important to constantly
			strive to facilitate an environment where all of our pets are as
			content and healthy as possible. In order to do this, we must
			establish trust with our pets and should constantly work to better
			our relationship with them. This means that we should always stop to
			question what our pets want and need from us.</p>

		<div id="blueWhiteInfo" STYLE="margin-bottom: 0">
			<div class="flexDisplay">
				<div class="fullFlex">
					<a id="haveAPet" class="anchor"></a>
					<div>
						<p class="writing">If you are preparing to purchase a pet,
							there are a few things that you should make sure to prepare for!
						</p>
						<ul id="petNeedsList" class="writing">
							<li>Do you have a shelter for the pet? Will the pet have a
								comfortable place to play, eat, and sleep every day?</li>
							<li>Do you know what kind of food is nutritious for your
								pet? What kinds of food can be used for an appealing treat?</li>
							<li>Do you want a pet that is active or is typically very
								relaxed? Do you want a pet that is very quiet? Animals of
								opposite sex can often have vastly different behavior. It is
								important to look info how different pets of different genders
								compare to your pet preferences and what you're looking for in a
								pet.</li>
							<li>Does the pet need to be walked frequently and does it
								spend a lot of time outside?</li>
							<li>Do you have an area for your pet to bathe and how
								frequently does it need to be cleaned?</li>
							<li>How big do you want your pet to be and do you have
								enough space for it?</li>
						</ul>
						<p class="writing">
							If you have any more questions about what you might need for
							purchasing any particular pet, you can search for that question
							on our <a href="ask.jsp"
								STYLE="font-size: unset; color: rgb(59, 31, 8)"> Ask About
								It</a> page!
						</p>
					</div>
				</div>
				<div id="bigSSContainer" class="right">
					<div id="slideshowContainer">
						<img src="img/sprinkles2.PNG"> <img src="img/henry.PNG">
						<img src="img/sprinkles3.PNG">
					</div>
				</div>
			</div>
			<div class="flexDisplay">
				<div id="bigSSContainer" class="left">
					<div id="slideshowContainer">
						<img src="img/henry3.PNG"> <img src="img/sprinkles.PNG">
						<img src="img/henry2.PNG">
					</div>
				</div>
				<div class="fullFlex">
					<a id="wantAPet" class="anchor"></a>
					<p class="writing">If you already have a pet, it could never
						hurt to try to improve your relationship with your pet! Your pet
						would benefit greatly not only from more attention, but also from
						a more focused effort to give it a more specific, comfortable, and
						stimulating routine every day.</p>
					<p class="writing">Is there a trick that you've always wanted
						to teach your pet, but never managed to find the time or
						consistency? Do you wish that your pet was more social or more
						comfortable around you? Are you concerned about your pet's
						behavior? Do you struggle to find time to feed your pet or get it
						to sleep at a good hour? Do you simply long for a closer bond with
						your pet?</p>
					<p class="writing">
						Well, if you do, you can simply <a href="trainingschedule.jsp"
							STYLE="font-size: unset; color: rgb(50, 82, 168)">make a
							schedule</a> for your pet every week to stay on track with your pet
						goals! This way, you can not only develop a better and more
						stimulating routine for your pet, but your can also get in more
						focused or planned quality time in while simultaneously knocking a
						few things off of your own daily check-list as well!
					</p>
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
</body>
</html>
