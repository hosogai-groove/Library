window.onload = function(){
	//受け取ったメッセージが正常に処理がされた場合のもの
	const okMsg = "貸出処理が正常に終了しました";
	var getMsg = document.getElementById("msg").value;
	var getRemaining = document.getElementById("remaining").value;
	//受け取ったメッセージが正常でない、また利用者の貸出可能数が0よりも大きい場合の処理
	if(getMsg != okMsg && getRemaining > 0){
		formChg();
	}
	
}
//エラー時に画面遷移先を変更するための処理
function formChg(){
	var buttonName = document.getElementById("buttonName");
	//ボタンの表示を変更する
	buttonName.value = "貸出書籍入力画面へ";
	//画面遷移先を変更する
	var getform = document.getElementById("form");
	getform.action = "LibrarySystem_LendSystem";
}