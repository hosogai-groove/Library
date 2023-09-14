package com.example.demo.RetComp;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.LendDelayConnect;
import com.example.demo.LendStateConnect;
import com.example.demo.MsgClass;

//返却確定画面のメイン処理
public class RetCompModel {
	private String RETURN_MSG = "ReturnMsg";
	private String ALREADY_RET = "AlreadyRet";
	private String RET_EXCEPTION = "RetException";
	
	public String delLendData(String bookData) {
		//受け取ったデータを書籍ごとに分ける
		String [] delBook = bookData.split(":");
		List <String> delList = new ArrayList<String>();
		String [] delData = new String[2];
		LendStateConnect lsc = new LendStateConnect();
		LendDelayConnect ldc = new LendDelayConnect();
		MsgClass msg = new MsgClass();
		String resultMsg = "";
		
		try {
			//貸出状況テーブルに返却対象の書籍があるかをチェックする
			delList = lsc.delChk(delBook);	
			
			
			for(int i = 0; i < delList.size(); i++) {
				//書籍番号と書籍枝番号に分ける
				delData = delList.get(i).split(",");
				//貸出状況テーブルから削除する
				lsc.delData(delData[0], delData[1]);
				//貸延滞管理テーブルから削除する
				ldc.dalaydel(delData[0], delData[1]);
			}
			
			if(delList.size() == delBook.length) {
				resultMsg = msg.msg(RETURN_MSG);
			}
			
			else{
				resultMsg = msg.msg(ALREADY_RET);
			}
			
			
			
		//Exception発生時
		}catch (Exception e) {
			e.printStackTrace();
			resultMsg = msg.msg(RET_EXCEPTION);
		}	
		return resultMsg;
	}
}
