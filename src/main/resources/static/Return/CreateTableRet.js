window.onload = function(){
	var dataString = document.getElementById("dataString").value;
		returnTable (dataString);
	
}
const thList = ["期限日", "残り期限", "書籍番号", "書籍名", "書籍枝番号", "返却"];

function returnTable (dataString){
	var tbl = document.createElement("table");
	tbl.id = "table";
	//該当のタグの後にテーブルタグを作成する
	document.getElementById("tableSet").after(tbl);
	var tr = document.createElement("tr");
	thtext = Array(thList.length);
	//項目名：残り期限 位置取得用
	var remDline = 0;
	//項目名：返却(チェックボックス設置位置) 位置取得用
	var returnCbox = 0;
	for(var i = 0; i < thList.length; i++){
		thtext[i] = document.createElement("th");

		thtext[i].textContent = thList[i];
		
		if(thList[i] == "残り期限"){
			remDline = i;
		}
		else if(thList[i] == "返却"){
			returnCbox = i;
		}
	
		//項目名：期限日、残り期限、書籍番号、書籍名、書籍枝番号、返却を設定する
		tr.appendChild(thtext[i]);
	}	
	tbl.appendChild(tr);
	
	var trdata = dataString.split(":");
	var tddata = "";
	var trArray = Array(trdata.length);
	var dataSize = document.createElement("input");
	dataSize.type = "hidden";
	dataSize.id = "dataSize";
	document.getElementById("dataString").after(dataSize);
	//利用者の貸出件数を取得する
	document.getElementById("dataSize").value = trdata.length;
	
	//取得件数分tdタグを設定する
	for(var i = 0; i < trdata.length; i++){
		var tddata = trdata[i].split(".");
		var creCheckbox = document.createElement("input");
		creCheckbox.type = "checkbox";
		creCheckbox.id = "cBox" + ":" + i;
		creCheckbox.value = tddata[1];
		creCheckbox.setAttribute("class", "checkBox");
		creCheckbox.setAttribute("onclick", "tableColor(this.id)");
		//取得件数分trタグを作成
		trArray[i] = document.createElement("tr");
		trArray[i].id = "tr" + ":" + i;
		trArray[i].dataset.book = tddata;

		for(var j = 0; j < thList.length; j++){
			//残り期限の場合は形を整形する
			if(j == remDline){
				var tdvalue = document.createElement("td");
				if(tddata[j] >= 0){
					tdvalue.textContent = tddata[j] +"日後";
				}
				else{
					tdvalue.textContent = "延滞" + tddata[j].replace("-", "") + "日";
					tdvalue.style.color = "red";
					trArray[i].style.backgroundColor = "#ffb6c1";
				}
				trArray[i].appendChild(tdvalue);
			}
			
			//返却項目である時はチェックボックスを設定する
			else if(j == returnCbox){
				var tdCheckbox = document.createElement("td");
				tdCheckbox.id = "td" + ":" + i;
				tdCheckbox.appendChild(creCheckbox);
				trArray[i].appendChild(tdCheckbox);
			}
			
			//取得データを各項目に設定する
			else{
				var tddata = trdata[i].split(".");
				var tdvalue = document.createElement("td");
				tdvalue.textContent = tddata[j];
				trArray[i].appendChild(tdvalue);
			}
		}
		//上記で設定されたtrタグをtableタグに埋め込む
		tbl.appendChild(trArray[i]);
		var boxData = document.getElementById("td" + ":" + i);
		boxData.value = trdata[i];
		boxData.className = "tablechkbox";
	}
	var table = document.getElementById("table");
	table.border = "1px";
	table.align = "center";
	returnDataSet();
}
