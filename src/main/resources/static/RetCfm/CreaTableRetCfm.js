window.onload = function(){
	var returnData = document.getElementById("returnData").value;
		retCfmTable (returnData);
	
}
const thList = ["期限日", "残り期限", "書籍番号", "書籍名", "書籍枝番号"];

function retCfmTable (returnData){
	var tbl = document.createElement("table");
	tbl.id = "table";
	//該当のタグの後にテーブルタグを作成する
	document.getElementById("tableSet").after(tbl);
	var tr = document.createElement("tr");
	thtext = Array(thList.length);
	//項目名：残り期限 位置取得用
	var remDline = 0;
	
	//項目名：書籍番号 位置取得用
	var bookNum = 0;
	
	//項目名：書籍枝番号 位置取得用
	var bookBNum = 0;
	
	
	//書籍番号,書籍枝番号保存用
	var bookData = "";

	for(var i = 0; i < thList.length; i++){
		thtext[i] = document.createElement("th");

		thtext[i].textContent = thList[i];
		
		if(thList[i] == "残り期限"){
			remDline = i;
		}
		
		else if(thList[i] == "書籍番号"){
			bookNum = i;
		}
		
		else if(thList[i] == "書籍枝番号"){
			bookBNum = i;
		}
	
		//項目名：期限日、残り期限、書籍番号、書籍名、書籍枝番号を設定する
		tr.appendChild(thtext[i]);

	}	
	tbl.appendChild(tr);
	
	var trdata = returnData.split(":");
	var tddata = "";
	var trArray = Array(trdata.length);
	
	//取得件数分tdタグを設定する
	for(var i = 0; i < trdata.length; i++){
		var tddata = trdata[i].split(",");
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
			}
			
			//取得データを各項目に設定する
			else{
				if(j ==  bookNum){
					bookData += tddata[j] + ",";
				}
				
				else if(j ==  bookBNum){
					bookData += tddata[j] + ":";
				}
				var tddata = trdata[i].split(",");
				var tdvalue = document.createElement("td");
				tdvalue.textContent = tddata[j];
			}
			trArray[i].appendChild(tdvalue);
		}
		//上記で設定されたtrタグをtableタグに埋め込む
		tbl.appendChild(trArray[i]);
	}
	var table = document.getElementById("table");
	table.border = "1px";
	table.align = "center";
	document.getElementById("bookData").value = bookData.slice(0, -1);
}
