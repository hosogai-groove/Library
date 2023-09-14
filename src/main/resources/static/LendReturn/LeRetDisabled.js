window.onload = function(){
	disabledSet();
}

function disabledSet(){
	//貸出上限数に達しているか否かの判定用
	var LendLimit = document.getElementById("LendLimit").textContent;
	//書籍の延滞があるか否かの判定用
	var DelayReturn = document.getElementById("DelayReturn").textContent;
	//累計延滞一覧よりペナルティフラグがtrueかfalseかの判定用
	var PenaltyFlag = document.getElementById("PenaltyFlag").textContent;
	//貸出書籍があるかないかの判定用
	var NotLend = document.getElementById("NotLend").textContent;
	
	//各項目のいずれかにメッセージが設定されているときの処理
	if(LendLimit != "" || DelayReturn != "" || PenaltyFlag != ""){
		//貸出ボタンを非活性状態にする
		document.getElementById("Lend").disabled = true;
	}
	
	//貸出書籍がなくメッセージが設定されているときの処理
	if(NotLend != ""){
		//返却ボタンを非活性状態にする
		document.getElementById("Return").disabled = true;
	}
	
}