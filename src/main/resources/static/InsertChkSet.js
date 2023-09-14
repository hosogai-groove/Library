function insertChk (id) {
	//2文字までの入力欄のパターン
	const pattern2chara =  '^[0-9]{1,2}$';
	
	//3文字までの入力欄のパターン
	const pattern3chara =  '^[0-9]{1,3}$';
	
	var chkId = document.getElementById(id + "text");
	var erFlag = document.getElementById(id + "error").value;
	var chkValue = chkId.value;
	var chFlag = false;
	
	//貸出上限数・貸出制限対象の入力チェック
	if(id == "lendLim" || id == "penaDays"){
		//パターンに当てはまればchFlagをtrueにする
		if(chkValue.match(pattern2chara)){
			if(chkValue > 0){
				chFlag = true;
			}
		}
	}
	
	//返却期限の入力チェック
	if(id == "deadline"){
		//パターンに当てはまり,範囲内であればchFlagをtrueにする
		if(chkValue.match(pattern2chara)){
			if(chkValue > 0 && chkValue < 31){
				chFlag = true;
			}
		}
	}
	
	//貸出制限期間・累計リセット期間の入力チェック
	if(id == "penaPeriod" || id == "resPeriod"){
		//パターンに当てはまり,範囲内であればchFlagをtrueにする
		if(chkValue.match(pattern3chara)){
			if(chkValue > 0 && chkValue < 366){
				chFlag = true;
			}
		}
	}
	
	//chFlagがtrueである場合正常に入力されたとして処理を行う
	if(chFlag == true){
		chkId.style.backgroundColor = "#ffffff";
		//該当箇所にエラーメッセージがある場合メッセージを削除する
		if(erFlag == "1"){
			document.getElementById(id + "Msg").remove();
			//該当のエラーフラグのvalueを0に設定する
			document.getElementById(id + "error").value = 0;	
		}	
	}
		
	//chFlagがtrueでない場合 入力値に問題があるとして処理を行う
	else{
		chkId.style.backgroundColor = "#ffb6c1";
		msgSet(id);
		//該当のエラーフラグのvalueを1に設定する
		document.getElementById(id + "error").value = 1;			
	}
	//valueChgflag()を呼び出す
	valueChgflag();
}