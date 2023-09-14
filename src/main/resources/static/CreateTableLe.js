window.onload = function(){
	createCfgTable();
}

//検索結果をテーブルで表示する
function createSrcTable(data, num){
	var tbl = document.createElement("table");
	tbl.id = "table";
	//該当のタグの後にテーブルタグを作成する
	document.getElementById("br" + num).after(tbl);
	//項目名をリストにまとめる
	var thList = ["書籍番号", "書籍名", "書籍枝番号"];
	var thtext = Array(thList.length);
	var tr = document.createElement("tr");
	for (var i = 0; i < thList.length; i++){
	
		thtext[i] = document.createElement("th");
		thtext[i].textContent = thList[i];

		//項目名：書籍番号、書籍名、書籍枝番号を設定する
		tr.appendChild(thtext[i]);
	}
	tbl.appendChild(tr);
			
	var bookData;
	var trArray = Array(data.length);
	
	//取得件数分tdタグを設定する
	for(var i = 0; i < data.length; i++){
		var crebutton = document.createElement("button");
		crebutton.innerHTML = "選択";
		//取得件数分trタグを作成
		trArray[i] = document.createElement("tr");
		bookData = JSON.stringify(data[i]);
		var replace1 = bookData.replace("{", "");
		var replace2 = replace1.replace("}", "");
		var replace3 = replace2.replace("'", "");
		var replace4 = replace3.replaceAll("\"", "");
		var split1 = replace4.split(",");
		crebutton.id = replace4;
				
		for(var j = 0; j < thList.length + 1; j++){
			//上記で設定したリストの長さ分のデータを設定する
			if(j < thList.length){
				var split2 = split1[j].split(":");
				var tdvalue = document.createElement("td");
				tdvalue.textContent = split2[1];
				trArray[i].appendChild(tdvalue);
			}
			//テーブルの最後の項目に選択ボタンを設定する
			else{
				var tdButton = document.createElement("td");
				tdButton.appendChild(crebutton);
				trArray[i].appendChild(tdButton);
			}
		}
		//上記で設定されたtrタグをtableタグに埋め込む
		tbl.appendChild(trArray[i]);
		//設定ボタンにvalueとonclickを設定する
		var buttonData = document.getElementById(replace4);
		buttonData.value = num;
		buttonData.className = "tablebutton";
		buttonData.setAttribute("onclick", "TableButton(this.id, this.value)");
	}
	var table = document.getElementById("table");
	table.border = "1px";
	table.align = "center";
}


//貸出確認用テーブルを作成する
function createCfgTable(){
	var tableData = document.getElementById("lendList").value;
	var splitData = tableData.split(",");
	
	
	
	var tbl = document.createElement("table");
	tbl.id = "table";
	
	//該当のタグの後にテーブルタグを設定する
	document.getElementById("lendList").after(tbl);
	var tr = document.createElement("tr");
	//項目名をリストにまとめる
	var thList = ["書籍番号", "書籍名", "書籍枝番号"];
	var lendNum = splitData.length / thList.length;
	var thtext = Array(thList.length);
	var bookNum = 0;
	var bookBNum = 0;
	var tr = document.createElement("tr");
	for (var i = 0; i < thList.length; i++){
	
		thtext[i] = document.createElement("th");
		//項目名：書籍番号、書籍名、書籍枝番号を設定する
		thtext[i].textContent = thList[i];
		//書籍番号が項目名の何番目に位置するかを(項目数分 - (i + 1))で取得
		if (thList[i] == "書籍番号"){
			bookNum = thList.length - (i + 1);
		}
		//書籍枝番号が項目名の何番目に位置するかを(項目数分 - (i + 1))で取得
		else if(thList[i] == "書籍枝番号"){
			bookBNum = thList.length - (i + 1);
		}

		//項目名：書籍番号、書籍名、書籍枝番号を設定する
		tr.appendChild(thtext[i]);
	}
	tbl.appendChild(tr);

	var trArray = Array(lendNum);
	var recodeList = Array(lendNum);
	var dataList =  Array(splitData.length);
	
	var j = 0;
	trArray[0] = document.createElement("tr");
	//データの数分tdタグを設定する
	for(var i = 0; i < splitData.length; i++){
		var tdvalue = document.createElement("td");
		tdvalue.textContent = splitData[i].split(":")[1];
		dataList[i] = splitData[i].split(":")[1];
		trArray[j].appendChild(tdvalue);
		//項目数で1区切りにする
		if((i + 1) % thList.length == 0){
			tbl.appendChild(trArray[j]);
			//書籍番号、書籍枝番号を":"区切りでリストにまとめる
			recodeList[j] = dataList[i - bookNum] +":"+ dataList[i - bookBNum];
			j++;
			trArray[j] = document.createElement("tr");
		}
	}
	//上記で作成した書籍番号、書籍枝番号のリストを値として設定する
	document.getElementById("lendRecodeList").value = recodeList;
	var table = document.getElementById("table");
	table.border = "1px";
	table.align = "center";
}






