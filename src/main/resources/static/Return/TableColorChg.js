function tableColor(id){
	var gettd = document.getElementById(id);
	var getDeadLine = gettd.value;
	var getArrayval = document.getElementById("tr" + ":" +id.split(":")[1]); 
	if(gettd.checked){
		getArrayval.style.backgroundColor = "#87cefa";
	}
	else{
		if(getDeadLine > 0){
			getArrayval.style.backgroundColor = "white";
		}
		else{
			getArrayval.style.backgroundColor = "#ffb6c1";
		}
	}
	returnDataSet();
}

function allCheck(){
	var dataSize = document.getElementById("dataSize").value;
	for(var i = 0; i < dataSize; i++){
		document.getElementById("tr" + ":" + (i)).style.backgroundColor = "#87cefa";
		document.getElementById("cBox" + ":" + (i)).checked = true;
	}
	returnDataSet();
}

function allClear(){
	var dataSize = document.getElementById("dataSize").value;
	for(var i = 0; i < dataSize; i++){
		var getArrayval = document.getElementById("tr" + ":" + (i));
		var checkBox = document.getElementById("cBox" + ":" + (i));
		checkBox.checked = false;
		if(checkBox.value > 0){
			getArrayval.style.backgroundColor = "white";
		}
		else{
			getArrayval.style.backgroundColor = "#ffb6c1";
		}
	}
	returnDataSet();
}