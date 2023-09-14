//貸出延滞管理テーブル接続処理
function lendDelayConnect(radioData){
	var firstOrder = radioData["fOrderSet"];
	var secondOrder = radioData["sOrderSet"];
	var thirdOrder = radioData["tOrderSet"];
	
	$.ajax({
		url:"LendDelayList",
		type:"POST",
		data:{
			fOrder:firstOrder[0],
			fOrderVal:firstOrder[1],
			sOrder:secondOrder[0],
			sOrderVal:secondOrder[1],
			tOrder:thirdOrder[0],
			tOrderVal:thirdOrder[1],
			dateData:radioData["date"],
			dateVal:radioData["dateValue"]

		}
		
	}).done(function(data){	
		if(data != ""){
			creTableLeDelay(data);
		}

		
	}).fail(function(){
		
	}).always(function(){
		
	})
 }