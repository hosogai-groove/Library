//テーブル内の選択ボタン押下時の処理
function TableButton(id, num){
	var bookNum = document.getElementById("bookNum" + num);
	var bookName = document.getElementById("bookName" + num);
	var bookBNum = document.getElementById("bookBNum" + num);
	var errorMsg = document.getElementById("errorMsg" + num);
	
	//該当箇所にエラーメッセージがある時はメッセージを削除する
	if(errorMsg != null){
		errorMsg.remove();
	}
	
	//背景色を白に変更する
	bookNum.style.backgroundColor = "white";
	bookName.style.backgroundColor = "white";
	bookBNum.style.backgroundColor = "white";
	
	var bookData = id.split(",");
	//","によって区切ると書籍番号,書籍名,書籍枝番号で分けられるため順に入力欄に設定する
	for (var i = 0; i < bookData.length; i++){

		var dataValue = bookData[i].split(":");

		if(i == 0){
			bookNum.value = dataValue[1];
		}
		else if(i == 1){
			bookName.value = dataValue[1];
		}
		else{
			bookBNum.value = dataValue[1];
		}
	}
	
	document.getElementById("id" + num).value = id + "." + num;
	document.getElementById("table").remove();	
	lendFlagChk();
}