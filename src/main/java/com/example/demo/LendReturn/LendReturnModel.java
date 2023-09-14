package com.example.demo.LendReturn;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.Cast;
import com.example.demo.LendStateConnect;
import com.example.demo.MsgClass;
import com.example.demo.StDbConnect;
import com.example.demo.TotalDelConnect;

public class LendReturnModel {
	private final static String LEND_LIMIT = "LendLimit";
	private final static String DELAY_RETURN = "DelayReturn";
	private final static String PENALTY_FLAG = "PenaltyFlag";
	private final static String NOT_LEND = "NotLend";

	public HashMap <String, String> chkDatabase(String cardNumber)throws Exception{
		StDbConnect sdc = new StDbConnect();
		LendStateConnect lsc = new LendStateConnect();
		TotalDelConnect tdc = new TotalDelConnect();
		HashMap <String, String> returnMap = new LinkedHashMap<String, String>();
		MsgClass msg = new MsgClass();
		Cast cast = new Cast();

		//貸出上限数を設定用データベースからint型に変換し取得する
		int lendLim = cast.listMapCastInt(sdc.getLendingLim());
		//利用者の現在の貸出書籍数を貸出状況テーブルからint型に変換し取得する
		int lendbook = cast.listMapCastInt(lsc.getCount(cardNumber));
		int delayReturn = 0;

		
		//利用者の貸出書籍数が貸出上限数以上であるとき,Mapの貸出上限数の値にメッセージを設定する
		if (lendLim <= lendbook) {
			returnMap.put(LEND_LIMIT, msg.msg(LEND_LIMIT));	
		}
		//利用者の貸出書籍数が貸出上限数より少ないとき,Mapの貸出上限数の値に空文字を設定する
		else {
			returnMap.put(LEND_LIMIT, "");
		}
		
		returnMap.put("remaining", String.valueOf(lendLim - lendbook));
		
		//利用者の貸出書籍数が1冊以上であるときMapの貸出書籍数の値に空文字を設定する
		if(lendbook >= 1) {
			delayReturn = cast.listMapCastInt(lsc.getDelay(cardNumber));
			returnMap.put(NOT_LEND, "");
		}
		//利用者の貸出書籍数が0冊であるときMapの貸出書籍数の値にメッセージを設定する
		else {
			returnMap.put(NOT_LEND, msg.msg(NOT_LEND));
		}
		
		//貸出書籍に延滞があるとき、Mapの延滞返却の値にメッセージを設定する
		if (delayReturn >= 1) {
			returnMap.put(DELAY_RETURN, msg.msg(DELAY_RETURN));
		}
		//貸出書籍に延滞がないとき、Mapの延滞返却の値に空文字を設定する
		else {
			returnMap.put(DELAY_RETURN, "");
		}

		//累計延滞管理テーブルからデータを取得する
		List<Map<String, Object>> penaData = tdc.getPenalty(cardNumber);
		boolean penaFlag = false;
		//累計延滞管理テーブルに利用者のデータがある場合,取得データを整形しペナルティフラグに設定する
		if (penaData.size() > 0) {
			penaFlag = cast.listMapCastbole(penaData);
		}
		//ペナルティフラグがtrueであるとき、Mapのペナルティフラグの値にメッセージを設定する
		if (penaFlag == true) {
			returnMap.put(PENALTY_FLAG, msg.msg(PENALTY_FLAG));
		}
		//ペナルティフラグがtrueでないとき、Mapのペナルティフラグの値に空文字を設定する
		else {
			returnMap.put(PENALTY_FLAG, "");
		}
		
		return returnMap;
	}
}
