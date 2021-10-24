var mediaQueryList = window.matchMedia('(min-width : 750px)');
mediaQueryList.addEventListener('change', screenTest);

var x = document.getElementById("smallmenu");
var line1 = document.getElementById("line1");
var line2 = document.getElementById("line2");
var navsm = document.getElementById("smallnav");
var footer = document.getElementById("footer");
var menuchildren = document.getElementById("smallList").children;
var darkcover = document.getElementById("darkcover");
var len = menuchildren.length;
var menudown = false;


var i;
for (i = 0; i < x.length; i++) {
  x[i].style.backgroundColor = "red";
}

x.addEventListener("click", makeHideX);

function makeHideX(){
	if (menudown == false) {
		
		line1.style.animation = "rotate-slide-top 0.3s ease-in-out forwards";
		line2.style.animation = "rotate-slide-bottom 0.3s ease-in-out forwards";
		navsm.style.display = "block";	
		
		
		//document.body.style.backgroundColor = "red";
		var i;
		for (i = 0; i < len; i++) {
			menuchildren[i].style.opacity = "0";
			rotateElems(i);
		} 
		darkcover.style.background = "rgba(0,0,0,0.5)";
		darkcover.style.display = "block";
		menudown = true;
	} 
	else {
		line1.style.animation = "rotate-slide-top-rev 0.3s ease-in-out forwards";
		line2.style.animation = "rotate-slide-bottom-rev 0.3s ease-in-out forwards";
		var i;
		for (i = 0; i < len; i++){
			menuchildren[i].style.opacity = "1";
			rotateElemsRev(i);
		}
		menudown = false;
		darkcover.style.background = "none";
		darkcover.style.display = "none";
	}
}

function screenTest(e) {
	if (e.matches) {
		line1.style.animation = "none";
		line2.style.animation = "none";
		navsm.style.display = "none";
		navsm.style.backgroundColor = "none";
		menudown = false;
		darkcover.style.background = "none";
		darkcover.style.display = "none";
	}
}

function rotateElems(i){
	var del_string = 0.15 * i;
	var del = del_string.toString();
	var dels = del.concat("s");
	var pt1 = "menuAnim 0.15s ease-in forwards ";
	var anim = pt1.concat(dels);
	menuchildren[i].style.animation = anim;
}
		
function rotateElemsRev(i){
	var del_string = 0.15 * (len - i);
	var del = del_string.toString();
	var dels = del.concat("s");
	var pt1 = "menuAnim-rev 0.15s ease-in forwards ";
	var anim = pt1.concat(dels);
	menuchildren[i].style.animation = anim;
}

