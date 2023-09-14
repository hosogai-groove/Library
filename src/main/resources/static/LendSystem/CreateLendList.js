//貸出用の書籍をリストでまとめる
function createList(){
	//利用者の貸出可能数を取得する
	var lendLim = document.getElementById("lendLim").value;
	var lendList = [];
	
	for(var i = 1; i <= lendLim; i++){
		var lendData = document.getElementById("id" + i).value;
		//該当の入力欄に入力がある場合は書籍情報をリストへ追加する
		if(lendData != 0){
			lendList.push(lendData.split(".")[0]);
		}
	}
	//作成したリストを設定する
	document.getElementById("lendData").value = lendList;
	document.formName.submit();
}