//画面が読み込まれたらvalueChgflag()を呼び出す
window.onload = function(){
	valueChgflag();
}
//設置変更ボタンの制御とフラグの処理を行う
function valueChgflag(){
	//設定用データベース全項目をcolKeyに登録する
	const colKey = ["lendLim", "deadline", "dlCount", "dlCountT", "dlCountM", "penaDays", 
	"penaPeriod", "resPeriod", "urgeMail", "mailSendT"];
	//設定用データベースからtext形式で直接入力する項目をtextKeyに登録する
	const textKey = ["lendLim","deadline", "penaDays", "penaPeriod", "resPeriod"];

	//変更判定用フラグ
	var chgFlag = 0;
	//エラー判定用フラグ
	var erChgFlag = 0;
	
	
	//colKeyの項目数分だけ繰り返す
	for(var i = 0; i < colKey.length; i++){
		//現在の入力値と設定用データベースの値が異なる場合chgFlagを1にして繰り返しを抜ける
		if(document.getElementById(colKey[i] + "text").value != document.getElementById(colKey[i] + "bfSet").value){
			chgFlag = 1;
			break;
		}
	}
	//textKeyの項目数分だけ繰り返す
	for(var i = 0; i < textKey.length; i++){
		//該当箇所のエラーフラグが1である場合はerChgFlagを1にして繰り返しを抜ける
		if(document.getElementById(textKey[i] + "error").value == 1){
		erChgFlag = 1;
		break;
		}
	}
	//chgFlagが1でありerChgFlagが0である場合「設定変更ボタン」を活性状態にする
	if(chgFlag > 0 && erChgFlag < 1){
		document.getElementById("settingChg").disabled = false;
	}
	//上記以外の場合「設定変更ボタン」を非活性状態にする
	else{
		document.getElementById("settingChg").disabled = true;
	}
}