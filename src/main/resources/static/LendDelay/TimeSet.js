window.onload = function(){
	var num = document.getElementById("days").value;
	getToDay(num);
}

function getToDay(num){
	var todayData = new Date();
	todayData.setDate(todayData.getDate() - num);
	var year = todayData.getFullYear();
	var month = todayData.getMonth()+1;
	var day = todayData.getDate();
	
	month = ("0" + month).slice(-2);
	day = ("0" + day).slice(-2);
	var standard = year + "/" + month + "/" + day;
	getRadio(standard);	
}