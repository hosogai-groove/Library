package com.example.demo.Return;

import java.util.List;
import java.util.Map;

import com.example.demo.LendStateConnect;
import com.example.demo.TimeCompare;

public class ReturnModel {
//利用者の貸出情報を文字列にまとめるメソッド
	public String getBookData (String cardNumber) {
		LendStateConnect lsc = new LendStateConnect();
		String lendData = "";
		try {
			//利用者の図書カード番号から返却期限、書籍番号、書籍枝番号を取得する
			List <Map<String,Object>> lendList = lsc.getLendData(cardNumber);
			//上記で取得したList<Map>のMapのキー名を配列に格納する
			String[] keyArray = {"returnDate", "bookNumber", "bookName", "bookBranchNumber"};
			String replaceFormat = "";
			TimeCompare tc = new TimeCompare();
			for (int i = 0; i < lendList.size(); i++) {
				for (int j = 0; j < keyArray.length; j++) {
					//返却期限の処理
					if(j == 0) {
						//"yyyy-MM-dd"から"yyyy/MM/dd"に整形する
						replaceFormat = (lendList.get(i).get(keyArray[j])).toString().replace("-", "/");
						//区切り文字用の"."を末尾につけ、文字列に追記
						lendData += replaceFormat + ".";
						//返却日までの日数を取得し、文字列に追記
						lendData += tc.timeCompare(replaceFormat);	
					}
					else {
						//文字列に追記
						lendData += lendList.get(i).get(keyArray[j]);
					}
					if(j != keyArray.length-1) {
						//区切り文字用の"."をつける
						lendData += ".";
					}
				}
				if(i != lendList.size()-1) {
					//区切り文字用の":"をつける
					lendData += ":";
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lendData;
	}
}
