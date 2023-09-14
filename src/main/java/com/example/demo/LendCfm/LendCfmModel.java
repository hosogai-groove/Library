package com.example.demo.LendCfm;

import java.util.List;
import java.util.Map;

import com.example.demo.LendStateConnect;
import com.example.demo.MsgClass;
import com.example.demo.StDbConnect;

//貸出処理用のクラス
public class LendCfmModel {
	private final String LEND = "lend";
	private final String ERROR_LEND = "errorlend";
	public final String LEND_EXCEPTION = "LendException";
	//書籍貸出処理メソッド
	public String lendRgs(String lendData, String cardNumber){
		LendStateConnect lsc = new LendStateConnect();
		StDbConnect stdb = new StDbConnect();
		//書籍情報を","で区切り配列にする
		String[] bookRecode = lendData.split(",");
		String lendmsg = "";
		MsgClass msg = new MsgClass();
		
		try {
			//貸出書籍の中に貸出状況テーブルに登録されているものがないかを確認し、登録がないもののみを取得する
			List<String> canLendList = lsc.getChkLend(bookRecode);
			//貸出期限数を取得する
			List<Map<String, Object>> getReDeadline = stdb.getRedate();
			//リストから貸出期限数を取り出しint型へキャストする
			int reDeadline = Integer.parseInt((getReDeadline.get(0).get("settingValue")).toString());
			//貸出書籍を貸出状況テーブルへ登録する
			lsc.lendBookRgs(canLendList, cardNumber, reDeadline);
			
			if(bookRecode.length == canLendList.size()){
				//貸出完了メッセージを設定する
				lendmsg = msg.msg(LEND);
			}
			else {
				//貸出時エラーメッセージ
				lendmsg = msg.msg(ERROR_LEND);
			}
		
		//Exception発生時のエラーメッセージ設定
		}catch (Exception e) {
			e.printStackTrace();
			lendmsg = msg.msg(LEND_EXCEPTION);
		}
		return lendmsg;
	}
}
