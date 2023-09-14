//設定機能:エラーメッセージ表示用

function msgSet (id) {
	//貸出上限数エラー時メッセージ
	const lendLimMes = "貸出上限数は半角数字1桁以上2桁以下で1～99の範囲で入力してください";
	//返却期限エラー時メッセージ
	const deadlineMes = "返却期限は半角数字1桁以上2桁以下で1～30の範囲で入力してください";
	//貸出制限対象エラー時メッセージ
	const penaDaysMes = "貸出制限対象は半角数字1桁以上2桁以下で1～99の範囲で入力してください";
	//貸出制限期間エラー時メッセージ
	const penaPeriodMes = "貸出制限期間は半角数字1桁以上3桁以下で1～365の範囲で入力してください";
	//累計リセット期間エラー時メッセージ
	const resPeriodMes = "累計リセット期間は半角数字1桁以上3桁以下で1～365の範囲で入力してください";

	const msgMapList = new Map();
	
	//Mapでメッセージと使用箇所を紐づける
	msgMapList.set("lendLim", lendLimMes);
	msgMapList.set("deadline", deadlineMes);
	msgMapList.set("penaDays", penaDaysMes);
	msgMapList.set("penaPeriod", penaPeriodMes);
	msgMapList.set("resPeriod", resPeriodMes);

	//現在のエラーフラグを確認する(0 or 1)
	var erFlag = document.getElementById(id + "error").value;
	
	//エラーフラグが0の場合該当箇所にエラーメッセージ赤色で表示
	if(erFlag == "0"){
		error_element = document.createElement("div");
		error_element.textContent = msgMapList.get(id);
		error_element.id = id + "Msg";
		//該当のエラーフラグのあるタグの前にメッセージを追加する
		document.getElementById(id + "error").before(error_element);
		error_element.style.fontSize = "15px";
		error_element.style.color = "red";
	}
}