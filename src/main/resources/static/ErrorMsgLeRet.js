
function msgSet (id) {
	
	//書籍番号が見つからない時の文字列パターン
	const patternNFB = "^notFoundBook";
	
	//書籍枝番号が見つからない時の文字列パターン
	const patternNFBN = "^notFoundBookN";
	
	//書籍番号である時の文字列パターン
	const patternEBNnum = "^bookNum";
	
	//書籍枝番号である時の文字列パターン
	const patternEBBNnum = "^bookBNum";
	
	//貸出上限数エラー時メッセージ
	//図書カード番号入力時形式エラーメッセージ
	const insertError = "図書カード番号は半角数字5桁固定で入力してください";

	//未登録図書カード番号入力時のエラーメッセージ
	const notFoundNum = "入力された図書カード番号は現在使用されていません";
	
	//未登録書籍番号入力時のエラーメッセージ
	const notFoundBookN = "該当の書籍番号がありません";
	
	//書籍番号、書籍枝番号の組み合わせが存在しない場合のエラーメッセージ
	const notFoundBook = "該当の書籍は存在しません";
	
	//書籍番号入力形式エラーメッセージ
	const bookNumError = "書籍番号の入力形式が正しくありません";
	
	//書籍枝番号入力形式エラーメッセージ
	const bookBNumError = "書籍枝番号は半角数字1桁で入力してください";
	
	//書籍が完全に同一のものが複数の入力欄に存在する場合のエラーメッセージ
	const duplication = "入力欄に同じ情報が複数入力されています";

	const msgMap = new Map();
	
	//Mapでメッセージと使用箇所を紐づける
	msgMap.set("insertError", insertError);
	msgMap.set("notFoundNum", notFoundNum);
	msgMap.set("notFoundBookN", notFoundBookN);
	msgMap.set("notFoundBook", notFoundBook);
	msgMap.set("bookNumError", bookNumError);
	msgMap.set("bookBNumError", bookBNumError);
	msgMap.set("duplication", duplication);
	
	//受け取った文字列が図書カード番号に関するものである場合の処理
	if(id == "insertError" || id == "notFoundNum"){
		var insertMsg = document.getElementById("cardmsg");
		//該当idにメッセージを設定する
		insertMsg.innerHTML = msgMap.get(id);
		insertMsg.style.color = "red";
		document.getElementById("cardNumtext").style.backgroundColor = "#ffb6c1";
		document.getElementById("nextbutton").disabled = true;
	}	
	
	
	//書籍番号、書籍枝番号入力時のエラー処理(書籍情報完全重複を除く)
	else if(id.match(patternEBNnum) || id.match(patternEBBNnum) || id.match(patternNFB)){
		//受け取った文字列パターンを":"で区切る
		var msgType = id.split(":");
		//該当箇所のエラーメッセージが表示されている場合はそのメッセージの削除する
		if(document.getElementById("errorMsg" + msgType[1]) != null){
			document.getElementById("errorMsg" + msgType[1]).remove();
		}
		//該当の入力欄の番号をidに含むdivタグを作成
		var createMsg = document.createElement("div");
		createMsg.id = "errorMsg" + msgType[1];
		//メッセージ表示位置調整用タグの後に上記のタグを設定する
		document.getElementById("errorMsg").after(createMsg);
		
		var insertMsg = document.getElementById("errorMsg" + msgType[1]);
		//該当の入力欄の番号とエラーメッセージを組み合わせたものを設定
		insertMsg.innerHTML = msgType[1] + "冊目:" + msgMap.get(msgType[0]);
		insertMsg.style.color = "red";
		
		//書籍番号が原因のエラーである場合の処理
		if(id.match(patternEBNnum)||id.match(patternNFBN)){
			//該当の書籍番号入力欄の背景色を赤くする
			document.getElementById("bookNum" + msgType[1]).style.backgroundColor = "#ffb6c1";
		}
		//書籍枝番号が原因のエラーである場合の処理
		if(id.match(patternEBBNnum)){
			//該当の書籍枝番号入力欄の背景色を赤くする
			document.getElementById("bookBNum" + msgType[1]).style.backgroundColor = "#ffb6c1";
		}
		//書籍番号と書籍枝番号の組み合わせがない場合
		if(id.match(patternNFB) && !id.match(patternNFBN)){
			//該当の書籍番号と書籍枝番号入力欄の背景色を赤くする
			document.getElementById("bookNum" + msgType[1]).style.backgroundColor = "#ffb6c1";
			document.getElementById("bookBNum" + msgType[1]).style.backgroundColor = "#ffb6c1";
		}
		
		document.getElementById("lendCfg").disabled = true;
	}

	
	//入力欄に書籍情報が完全一致するものが複数ある時の処理
	else if(id == "duplication"){
		//完全重複が存在するときのエラーメッセージがある場合は一度削除をする
		if(document.getElementById("errorMsg" + 100) != null){
			document.getElementById("errorMsg" + 100).remove();
		}
		
		//idに100を加えた(入力欄が最大99まで存在するため)divタグを設定する
		var createMsg = document.createElement("div");
		createMsg.id = "errorMsg" + 100;
		
		//メッセージ表示位置調整用タグの後に上記のタグを設定する
		document.getElementById("errorMsg").after(createMsg);
		
		var insertMsg = document.getElementById("errorMsg" + 100);
		insertMsg.innerHTML = msgMap.get("duplication");
		insertMsg.style.color = "red";
	}
}