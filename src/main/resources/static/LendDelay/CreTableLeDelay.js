//貸出延滞管理テーブル表示処理
function creTableLeDelay(dataList){
	
	//項目名をリストにまとめる
	const thList = ["期限日", "延滞日数", "図書カード番号", "延滞者名", "書籍番号", "書籍名", "書籍枝番号", "ジャンル名", "メール送信"];
	
	//受け取った貸出延滞管理テーブルのデータを取得するためのキーをリストにする
	//上記の項目名と取得予定のキーの位置を合わせる
	//延滞日数のキーは貸出延滞管理テーブルのデータに存在しないがこちらも項目名と位置を合わせて設定する
	const dataKey = ["deadLineDate", "delayDays", "cardNumber", "userName", "bookNumber", "bookName", "bookBranchNumber", "genreName", "mailFlag"];
		
	const mailSend = ["未送信", "延滞メール送信済み", "催促メール送信済み"];
	
	//テーブルタグを該当箇所に作成する
	var tbl = document.createElement("table");
	tbl.id = "table";
	document.getElementById("tableSet").after(tbl);
	var thtext = Array(thList.length);
	var tr = document.createElement("tr");
	
	//処理時の日付を取得
	var today = new Date();
	var year = today.getFullYear();
	var month = today.getMonth()+1;
	var day = today.getDate();
	month = ("0" + month).slice(-2);
	day = ("0" + day).slice(-2);
	var standard = year + "/" + month + "/" + day;
	
	for (var i = 0; i < thList.length; i++){
	
		thtext[i] = document.createElement("th");
		thtext[i].textContent = thList[i];

		//項目名を設定する
		tr.appendChild(thtext[i]);
	}
	tbl.appendChild(tr);
	
	var trArray = Array(dataList.length);

	for(var i = 0; i < dataList.length; i++){
		//取得件数分trタグを作成
		trArray[i] = document.createElement("tr");
		trArray[i].id = "tr" + ":" + i;

		for(var j = 0; j < dataKey.length; j++){
			if(dataKey[j] == "deadLineDate"){
				var tdvalue = document.createElement("td");
				tdvalue.textContent = dataList[i][dataKey[j]].replaceAll("-","/");
			}
			
			//延滞日数の時は形を整形する
			else if(dataKey[j] == "delayDays"){
				var tdvalue = document.createElement("td");
				var delaydate = new Date(dataList[i]["deadLineDate"].replaceAll("-","/"));
				var nowdate = new Date(standard);
				var diff = nowdate.getTime() - delaydate.getTime();
				tdvalue.textContent = diff / (24*60*60*1000)+ "日";
			}
			
			//メール送信の時は形を表示を変更する
			else if(dataKey[j] == "mailFlag"){
				var tdvalue = document.createElement("td");
				tdvalue.textContent = mailSend[dataList[i][dataKey[j]]];
			}			
						
			//取得データを各項目に設定する
			else{
				var tdvalue = document.createElement("td");
				tdvalue.textContent = dataList[i][dataKey[j]];
			}
			trArray[i].appendChild(tdvalue);
		}
		//上記で設定されたtrタグをtableタグに埋め込む
		tbl.appendChild(trArray[i]);
	}
	var table = document.getElementById("table");
	table.border = "1px";
	table.align = "center";
}
