function insertChkA (id) {
	
	//図書カード番号形式判定用パターン
	const pattern5chara = '^[0-9]{5}$';
	
	//書籍情報入力判定用パターン
	const idPattern = '^[0-9]{1,2}$';
	const ngIdPattern = '^0';
	
	//書籍枝番号判定用パターン
	const pattern1chara = '^[0-9]{1}$';
	
	//書籍番号判定用パターン
	const pattern6chara = '^[A-Z]{1}[0-9]{0,5}$';

	//idの文字列が図書カード番号を表している時の処理
	if(id == "cardNum"){
		var chkId = document.getElementById(id + "text");
		var chkValue = chkId.value;
		if(chkValue.match(pattern5chara)){
			userMstConnect();
		}
		else{
			msgSet("insertError");
		}
	}
	
	//idの文字列が書籍情報入力時を表している時の処理
	if(id.match(idPattern) && id != 0 && id != ngIdPattern){
		//該当の入力欄の書籍番号、書籍名、書籍枝番号の背景色を白にする
		var bookNumId = document.getElementById("bookNum" + id);
		var bookNameId = document.getElementById("bookName" + id);
		var bookBNumId = document.getElementById("bookBNum" + id);
		
		bookNumId.style.backgroundColor = "white";
		bookNameId.style.backgroundColor = "white";
		bookBNumId.style.backgroundColor = "white";
		
		//該当入力欄のエラーメッセージが存在する場合はメッセージを削除する
		if(document.getElementById("errorMsg" + id) != null){
			document.getElementById("errorMsg" + id).remove();
		}
		
		//書籍情報複数完全重複時エラーメッセージが存在する時はメッセージを削除する
		if(document.getElementById("errorMsg" + 100) != null){
			document.getElementById("errorMsg" + 100).remove();
		}
		
		//書籍枝番号違い複数同名書籍が存在する時はメッセージを削除する
		if(document.getElementById("carefulMsg") != null){
			document.getElementById("carefulMsg").remove();
		}
		
		//書籍情報のテーブルが表示されている時はそのテーブルを削除する
		if(document.getElementById("table") != null){
			document.getElementById("table").remove();
		}
		var numIdValue = bookNumId.value;
		var bNumIdValue = bookBNumId.value;
		
		//書籍番号の入力値に異常がない、もしくは入力がない時の処理
		if(numIdValue.match(pattern6chara) || numIdValue == ""){
			//書籍枝番号の入力値に異常がない、もしくは入力がない時の処理
			if(bNumIdValue.match(pattern1chara) || bNumIdValue == ""){
				insertbookNum(id);
			}
			//書籍番号にエラーがあるときメッセージを設定
			else{
				msgSet("bookBNumError" +":"+ id);
			}
		}
		//書籍枝番号にエラーがあるときメッセージを設定
		else{
			msgSet("bookNumError" +":"+ id);
		}
	}	
}