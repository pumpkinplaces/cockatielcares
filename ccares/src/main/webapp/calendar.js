const date = new Date();

const months = ["January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"];

const scheduleHeader = document.getElementById("theDay");
const alldays = document.getElementById("alldays");

function makeScheduleHeader() {
	var dateMonth = date.getMonth();
	var currentSquare = document.getElementById("selected");
	var dateYear = date.getFullYear();
	if (currentSquare.classList.contains("next-date")) {
		dateMonth += 1;
		if (dateMonth === 12) {
			dateYear += 1;
		}
	}
	if (currentSquare.classList.contains("prev-date")) {
		dateMonth -= 1;
		dateMonth += 12;
		if (dateMonth % 12 === 11) {
			dateYear -= 1;
		}
	}
	var currentDateString = currentSquare.innerHTML;
	var currentDate = (parseInt(currentDateString)).toString();
	var theMonth = months[(dateMonth % 12)];
	var fullDate = theMonth + " " + currentDate + " " + dateYear;
	scheduleHeader.innerHTML = fullDate;
}

function renderCalendar() {
	
	date.setDate(1);
	
	const thisYear = date.getFullYear();
	const thisMonth = date.getMonth();
	const monthDays = document.querySelector(".days");
	
	const lastDay = new Date(thisYear, thisMonth + 1, 0).getDate();
	const prevLastDay = new Date(thisYear, thisMonth, 0).getDate();
	const firstWeekDay = date.getDay();
	
	var counter = 0;
	var j = 0;
	
	document.querySelector(".date h1").innerHTML = months[date.getMonth()] + " " + date.getFullYear();
	document.querySelector(".date p").innerHTML = new Date().toDateString();
	
	let days = "";
	for (let x = firstWeekDay;x > 0; x--) {
		counter += 1;
		days +=  `<div class = "prev-date">${prevLastDay - x + 1}<div></div></div>`;
	}
	
	for (let i = 1; i <= lastDay; i++) {
		counter += 1;
		if (i === new Date().getDate() && date.getMonth() === new Date().getMonth() 
				&& date.getFullYear() === new Date().getFullYear()) {
			days += `<div class = "today" id = "selected">${i}<div></div></div>`;
		}
		else {
			days += `<div>${i}<div></div></div>`;
		}
	}

	while (counter < 42) {
		j += 1;
		counter += 1;
		days += `<div class = "next-date">${j}<div></div></div>`;
	}
	monthDays.innerHTML = days;
	
	document.querySelectorAll(".days div:not(.today)").forEach(
		item => {item.addEventListener("click", () => {
			document.querySelectorAll('.days div:not(item, .today)').forEach(otherItem => {
				otherItem.style.backgroundColor = "white"; 
				otherItem.removeAttribute("id");
			});
		document.querySelectorAll(".days .today").forEach(todayItem => {
			todayItem.style.color = "white"; todayItem.removeAttribute("id");});
			item.style.backgroundColor = "rgb(250, 240, 149)";
			item.setAttribute("id", "selected");
			makeScheduleHeader();
		});
	});

	document.querySelectorAll(".days .today").forEach(item => {item.addEventListener("click", () => {
		document.querySelectorAll('.days div:not(item, .today)').forEach(otherItem => {otherItem.style.backgroundColor = "white"; 
		otherItem.removeAttribute("id");
		});
		item.style.color = "rgb(250, 240, 149)";
		item.setAttribute("id", "selected");
		makeScheduleHeader();
		});
	});
	

}

renderCalendar();

document.querySelector(".prev").addEventListener("click", () => {
	date.setMonth(date.getMonth()-1);
	renderCalendar();
});

document.querySelector(".next").addEventListener("click", () => {
	date.setMonth(date.getMonth() + 1);
	renderCalendar();
});



