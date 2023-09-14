function insertText(id){
	//プルダウンメニューの現在の設定値を取得する
	var pdValue = document.getElementById(id + "pulldown").value;
	//該当のテキストボックスをプルダウンメニューで選択されている値に変更する
	document.getElementById(id + "text").value = pdValue;
	valueChgflag();
}