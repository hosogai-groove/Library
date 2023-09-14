package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


//貸出状況テーブル接続用クラス
public class LendStateConnect {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//貸出状況テーブルから利用者の貸出件数を取得する
	public List<Map<String, Object>> getCount(String cardNum) throws Exception{
		envFile e = new envFile();
		jdbcTemplate = e.jdbcTemplate();
		
		List<Map<String, Object>> lendCount = jdbcTemplate.queryForList("select count(*) from lendingstatus_table where cardNumber = ?", cardNum);
		
		return lendCount;
	}
	
	//貸出状況テーブルから利用者が延滞している件数を取得する
	public List<Map<String, Object>> getDelay(String cardNum) throws Exception{
		envFile e = new envFile();
		jdbcTemplate = e.jdbcTemplate();
		//処理実行時の日付を取得する
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		List<Map<String, Object>> delayCount = jdbcTemplate.queryForList("select count(*) from lendingstatus_table where cardNumber = ? and returnDate <= ?", cardNum, sdf.format(cal.getTime()));
		
		return delayCount;
	}
	
	//貸出状況テーブルに利用者が貸出しようとしている書籍がないことを確認する
	public List<String> getChkLend(String[] bookRecode) throws Exception{
		envFile e = new envFile();
		jdbcTemplate = e.jdbcTemplate();
		List<String> chkRecode = new ArrayList<String>();
		for(int i = 0; i < bookRecode.length; i++) {
			String [] lendBookData = bookRecode[i].split(":");
			List<Map<String, Object>> chkBookData = jdbcTemplate.queryForList("select count(*) from lendingstatus_table where bookNumber = ? and bookBranchNumber = ?", lendBookData[0], lendBookData[1]);
			int count = Integer.parseInt((chkBookData.get(0)).get("count(*)").toString());			
			if(count == 0){
				chkRecode.add(bookRecode[i]);
			}
		}
		return chkRecode;
	}
	
	//貸出状況テーブルに貸出状況を登録する
	public void lendBookRgs(List<String> bookList, String cardNumber, int reDeadline) throws Exception{
		envFile e = new envFile();
		jdbcTemplate = e.jdbcTemplate();
		//(処理実行時の日付＋貸出期限までの日数)を取得する
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, reDeadline);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String deadLine = sdf.format(cal.getTime());
			
		for(int i = 0; i < bookList.size(); i++) {
			String [] lendBookData = (bookList.get(i)).split(":");
			jdbcTemplate.update("Insert into lendingstatus_table (cardNumber, bookNumber, bookBranchNumber, returnDate)values(?, ?, ?, ?)", cardNumber, lendBookData[0], lendBookData[1], deadLine);				
		}
	}
	
	//貸出状況テーブルから利用者の貸出書籍情報を取得する
	public List<Map<String, Object>> getLendData(String cardNum) throws Exception{
		envFile e = new envFile();
		jdbcTemplate = e.jdbcTemplate();
		
		List<Map<String, Object>> bookData = jdbcTemplate.queryForList("select l.returnDate, l.bookNumber, b.bookName, l.bookBranchNumber from lendingstatus_table l inner join bookmst b on l.bookNumber = b.bookNumber and l.bookBranchNumber = b.bookBranchNumber where cardNumber = ?", cardNum);
		
		return bookData;
	}
	
	
	public List<String> delChk(String[] bookRecode) throws Exception{
		envFile e = new envFile();
		jdbcTemplate = e.jdbcTemplate();
		
		List<String> chkRecode = new ArrayList<String>();
		for(int i = 0; i < bookRecode.length; i++) {
			String [] lendBookData = bookRecode[i].split(",");

			List<Map<String, Object>> chkBookData = jdbcTemplate.queryForList("select count(*) from lendingstatus_table where bookNumber = ? and bookBranchNumber = ?", lendBookData[0], lendBookData[1]);
			int count = Integer.parseInt((chkBookData.get(0)).get("count(*)").toString());			
			if(count == 1){
				chkRecode.add(bookRecode[i]);
			}
		}
		return chkRecode;
	}
	
	
	//貸出状況テーブルから返却のため書籍を削除する
	public void delData(String bookNum, String bookBNum) throws Exception{
		envFile e = new envFile();
		jdbcTemplate = e.jdbcTemplate();
		jdbcTemplate.update("delete from lendingstatus_table where bookNumber = ? and bookBranchNumber = ?", bookNum, bookBNum);
		}
}