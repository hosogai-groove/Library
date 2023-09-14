package com.example.demo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeCompare {
	public String timeCompare(Object deadLine) throws ParseException {
		// "yyyy/MM/dd"の形で処理時日付を取得する
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String today = sdf.format(cal.getTime());
		
		//(返却期限 - 処理時の日付)の減算を行うため整形する
		Date todayDate = DateFormat.getDateInstance().parse(today);
		Date deadLineDate = DateFormat.getDateInstance().parse(deadLine.toString());
		long todayDateT =  todayDate.getTime();
		long deadLineDateT = deadLineDate.getTime();
		
		//日数で表示するための変数
		long one_date_time = 1000 * 60 * 60 * 24;
		
		//日数の差分をString型にキャストする
		String diffDays = String.valueOf((deadLineDateT - todayDateT)/one_date_time);
		
		return diffDays;
	}
}
