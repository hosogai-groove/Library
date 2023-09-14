//クリアボタン押下時の処理
function ClearButton (num){
	
	var bookNum = document.getElementById("bookNum" + num);
	var bookName = document.getElementById("bookName" + num);
	var bookBNum = document.getElementById("bookBNum" + num);

	//書籍番号・書籍名・書籍枝番号の入力欄を空にする
	bookNum.value = "";
	bookName.value = "";
	bookBNum.value = "";
	
	//書籍番号・書籍名・書籍枝番号の入力欄の背景色を白くする
	bookNum.style.backgroundColor = "white";
	bookName.style.backgroundColor = "white"
	bookBNum.style.backgroundColor = "white"
	
	
	//該当の入力欄のエラーメッセージを削除する
	if(document.getElementById("errorMsg" + num) != null){
		document.getElementById("errorMsg" + num).remove();
	}
	
	//同一書籍完全重複のエラーメッセージを削除する
	if(document.getElementById("errorMsg" + 100) != null){
			document.getElementById("errorMsg" + 100).remove();
	}
	
	//同一書籍枝番号違い時の注意メッセージを削除する
	if(document.getElementById("carefulMsg") != null){
		document.getElementById("carefulMsg").remove();
	}
	//該当箇所の書籍データを削除する
	document.getElementById("id" + num).value = 0;
	lendFlagChk();
}