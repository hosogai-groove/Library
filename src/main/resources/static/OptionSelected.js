window.onload = function() {
	//設定用データベースからプルダウンメニューから入力する項目をpulldownArrayに登録する
	var pulldownArray = ["dlCount", "dlCountT", "dlCountM", "urgeMail", "mailSendT"];
	
	for(var i = 0; i < pulldownArray.length; i++){
		selected (pulldownArray[i]);
	}
}

function selected (id){
	//該当項目の値を取得する
	var textValue = document.getElementById(id + "text").value;
	//該当箇所のプルダウンメニューを取得する
	var select =  document.getElementById(id + "pulldown");
	//取得したプルダウンメニューの項目数分だけ繰り返す
	for(var i = 0; i < select.length; i++){
		//プルダウンメニューのvalueを取得する
		seloption = select.options[i];
		opValue = seloption.value;
		//テキストの入力値をプルダウンメニューで選択状態にする
		if(textValue == opValue){
			seloption.setAttribute("selected", true)
		}
	}

}