
function insertbookNum(num){
	//該当箇所の入力値を取得
	var bookNum = document.getElementById("bookNum"+ num).value;
	//書籍情報保持用タグの値を"0"に設定する
	document.getElementById("id" + num).value = 0;
	//貸出確認ボタンを非活性にする
	document.getElementById("lendCfg").disabled = true;
	
	//書籍番号に何らかの入力があるときの処理
	if(bookNum != ""){
		lendSysConnect(num);
	}
}
 
function lendSysConnect(num){
	//該当箇所の書籍番号を取得
	var bookNum = document.getElementById("bookNum" + num).value;
	
	//該当箇所の書籍枝番号を取得
	var bookBNum = document.getElementById("bookBNum" + num).value;
	
	$.ajax({
		url:"LendingBook",
		type:"POST",
		data:{
		bookNum: bookNum,
		bookBNum: bookBNum,
		}

	}).done(function(data){
		if(data != ""){
			//上記の書籍情報で書籍を取得できた時テーブルを作成する
			createSrcTable(data, num);
		}
		//該当の書籍が見つからなかった時の処理
		else{
			//書籍枝番号に入力がない場合書籍番号エラー時のメッセージを設定する
			if(bookBNum == ""){
				msgSet("notFoundBookN:" + num);	
			}
			//書籍枝番号に入力があるときは書籍番号と書籍枝番号の組み合わせがない時のエラーメッセージを設定する
			else{
				msgSet("notFoundBook:" + num);	
			}
			
		}

	}).fail(function(){
		lendFlagChk();
		
	}).always(function(){
		
	})
 }