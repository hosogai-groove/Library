function returnDataSet(){
	var dataSize = document.getElementById("dataSize").value;
	var returnData = document.getElementById("returnData");
	var getData = "";
	var count = 0;
	for (var i = 0; i < dataSize; i++){
		if(document.getElementById("cBox" + ":" + i).checked == true){
			getData += document.getElementById("tr" + ":" + i).dataset.book + ":";
			count++;
		}
	}
	if (count > 0){
		document.getElementById("retButton").disabled = false;
		document.getElementById("allclear").disabled = false;
	}
	else{
		document.getElementById("retButton").disabled = true;
		document.getElementById("allclear").disabled = true;
	}
	
	if(count == dataSize){
		document.getElementById("allselect").disabled = true;
	}
	else {
		document.getElementById("allselect").disabled = false;
	}
	returnData.value = getData.slice(0, -1);
}