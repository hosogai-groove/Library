function getRadio(date){
	//優先項目の日数,利用者名,書籍名を取得するカラム名をリストにする
	const orderName = ["l.deadLineDate", "u.userName", "b.bookName"];
	//既にテーブルが表示されているときはテーブルを削除しておく
	if(document.getElementById("table") != null){
		document.getElementById("table").remove();
	}
	//第1優先項目取得用
	var order1 = document.getElementsByName("firstSort");
	//第2優先項目取得用
	var order2 = document.getElementsByName("secondSort");
	//日付の以上、以下取得用
	var dateOrder = document.getElementsByName("daysZone");
	
	var lengthF = order1.length;
	var lengthS = order2.length;
	
	var dateLength = dateOrder.length;
	
	var fOrderValue = "";
	var sOrderValue = "";
	var tOrderValue = "";
	
	var fOrderSet = "";
	var sOrderSet = "";
	var tOrderSet = "";
	
	var dateValue = "";
	
	//第1優先項目でチェックされている値を第2優先項目では選択できないようにする
	for (var i = 0; i < lengthF; i++){
		if(order1.item(i).checked){
			fOrderValue = order1.item(i).value;
			order2.item(i).disabled = true;
			var forder = document.getElementsByName(fOrderValue);
			for(var j = 0; j < forder.length; j++){
				if(forder.item(j).checked){
					fOrderSet = [fOrderValue, forder.item(j).value];
				}
			}
		}
		else{
			order2.item(i).disabled = false;
		}
	}
	
	//第2優先項目でチェックされている値を第1優先項目では選択できないようにする
	for (var i = 0; i < lengthS; i++){
		if(order2.item(i).checked){
			sOrderValue = order2.item(i).value;
			order1.item(i).disabled = true;
			var sorder = document.getElementsByName(sOrderValue);
			for(var j = 0; j < sorder.length; j++){
				if(sorder.item(j).checked){
					sOrderSet = [sOrderValue, sorder.item(j).value];
				}
			}
		}
		else{
			order1.item(i).disabled = false;
		}
	}
	
	//第1,第2優先項目のどちらにも選択されなかった項目を取得
	var filt1 = orderName.filter(item => (item.match(fOrderValue)) == null);
	var filt2 = filt1.filter(item => (item.match(sOrderValue)) == null);
	tOrderValue = filt2[0];
	
	var torder = document.getElementsByName(tOrderValue);
	for(var j = 0; j < torder.length; j++){
		if(torder.item(j).checked){
			tOrderSet = [tOrderValue, torder.item(j).value];
		}
	}
	//絞り込み日数が以上か以下かを取得する
	for (var i = 0; i < dateLength; i++){
		if(dateOrder.item(i).checked){
			dateValue = dateOrder.item(i).value;
		}
	}	
	
	var orderList = {fOrderSet, sOrderSet, tOrderSet, dateValue, date};
	
	lendDelayConnect(orderList);
}