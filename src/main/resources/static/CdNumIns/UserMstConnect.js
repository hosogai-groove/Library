//利用者マスタ接続処理
function userMstConnect(){
	//図書カード番号入力欄の値を取得する
	 var cardNum = $('#cardNumtext').val(); 
	 $.ajax({
		url:"SearchUserList",
		type:"POST",
		data:{
			key:cardNum,
	}
		
	}).done(function(data){	
		//入力された図書カード番号が存在するときの処理
		if(data != ""){
			//該当箇所のメッセージと文字色を正常時に設定する
			var insertMes = document.getElementById("cardmsg");
			insertMes.style.color = "black";
			insertMes.innerHTML = "図書館カード番号を入力してください";
			//入力欄の背景色を白に設定する
			document.getElementById("cardNumtext").style.backgroundColor = "white"
			document.getElementById("nextbutton").disabled = false;			
			document.getElementById("cardData").value = JSON.stringify(data[0]);
		}
		//入力された図書カード番号が存在しないときエラーメッセージを設定
		else{
			msgSet("notFoundNum");
		}
		
		
	}).fail(function(){
		
		
	}).always(function(){
		
	})
 }