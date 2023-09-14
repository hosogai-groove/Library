function insertChk(num){
	
	//0～999の数字を対象とする
	const daysPattern = '^[0-9]{1,3}$';
	//先頭が0である場合をNGとする
	const ngPattern = '^0';
	
	//上記のパターンを満たす時テーブルを表示させる
	if (num.match(daysPattern) && !num.match(ngPattern)){
		//エラーメッセージがある時はメッセージを削除する
		if(document.getElementById("msg") != null){
			document.getElementById("msg").remove();
		}
		getToDay(num);
	}
	//満たさない時はメッセージを表示する
	else{		
		//エラーメッセージがないときのみメッセージを作成する
		if(document.getElementById("msg") == null){
			//テーブルが表示されている時はテーブルを削除する
			if(document.getElementById("table") != null){
				document.getElementById("table").remove();
			}
		
			var msg = document.createElement("div");
			msg.id = "msg";
			document.getElementById("tableSet").after(msg);
			document.getElementById("msg").innerHTML = "延滞日数は1～999で設定してください";
		}
	}
}