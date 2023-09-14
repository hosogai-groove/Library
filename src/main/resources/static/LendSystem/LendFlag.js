window.onload = function(){
	lendFlagChk();
}

//貸出確認ボタン活性/非活性制御処理
function lendFlagChk(){
	//利用者の貸出可能数を取得する
	var insertSize = document.getElementById("lendLim").value;
	var insertFlag = false;
	var matchFlag = false;
	var insertData = [];
	
	//利用者の貸出可能数分だけ処理を行う
	for(var i = 1; i <= insertSize; i++){
		var flagChk = document.getElementById("id" + i).value;
		
		document.getElementById("bookNum" + i).style.backgroundColor = "white";
		document.getElementById("bookName" + i).style.backgroundColor = "white";
		document.getElementById("bookBNum" + i).style.backgroundColor = "white";
		
		//該当idで取得した値が"0"でない時は
		if(flagChk != 0){
			insertFlag = true;
			insertData.push(flagChk);
		}
	}
	
	//書籍情報重複判定処理
	for(var i = 0; i < insertData.length-1; i++){
		for(var j = i + 1; j < insertData.length; j++){
			var searchData = insertData[i].split(".");
			var conpareData = insertData[j].split(".");
			//比較対象とデータが健全一致する場合は両方の入力欄を赤くする
			if(searchData[0] == conpareData[0]){
				//重複フラグをtrueにする
				matchFlag = true;
				
				document.getElementById("bookNum" + searchData[1]).style.backgroundColor = "#ffb6c1";
				document.getElementById("bookName" + searchData[1]).style.backgroundColor = "#ffb6c1";
				document.getElementById("bookBNum" + searchData[1]).style.backgroundColor = "#ffb6c1";
				
				document.getElementById("bookNum" + conpareData[1]).style.backgroundColor = "#ffb6c1";
				document.getElementById("bookName" + conpareData[1]).style.backgroundColor = "#ffb6c1";
				document.getElementById("bookBNum" + conpareData[1]).style.backgroundColor = "#ffb6c1";
				
			}
			//書籍番号が一致するものがあるときの処理
			else if(searchData[0].split(":")[1] == conpareData[0].split(":")[1]){
				var createMsg = document.createElement("div");
				createMsg.id = "carefulMsg";
				//注意喚起用メッセージのidを設定したdivタグを作成する
				document.getElementById("errorMsg").after(createMsg);
				//作成したdivタグのtextを設定する
				document.getElementById("carefulMsg").innerHTML = "同一の書籍が入力されています";
			}
		}
	}	
	//入力値判定フラグがtrue、重複フラグがfalseの時のみの処理
	if (insertFlag == true && matchFlag == false){
		//貸出確認ボタンを活性状態にする
		document.getElementById("lendCfg").disabled = false;
	}
	
	else{
		//貸出確認ボタンを非活性状態にする
		document.getElementById("lendCfg").disabled = true;
		//重複フラグがtrueの時重複エラーメッセージを設定する
		if(matchFlag == true){
			msgSet("duplication");
		}
	}
}