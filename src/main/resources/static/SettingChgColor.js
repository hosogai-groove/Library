window.onload = function(){
	setColor();
}

function setColor(){
	var chgKeyList = document.getElementById("chgKeyList").value;
	var replace1 = chgKeyList.replace("[", "");
	var replace2 = replace1.replace("]", "");
	
	var splitValue = replace2.split(", ");
	
	for(i = 0 ; i < splitValue.length; i++){
		document.getElementById(splitValue[i]).style.color = "red";
	}
}