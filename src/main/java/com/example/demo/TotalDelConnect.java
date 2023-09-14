package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

//累計延滞管理テーブル接続用クラス
public class TotalDelConnect {
	private JdbcTemplate jdbcTemplate;
	
	//利用者のペナルティを確認する
	@Autowired
	public List<Map<String, Object>> getPenalty(String cardNum) throws Exception{
		envFile e = new envFile();
		jdbcTemplate = e.jdbcTemplate();
		
		List<Map<String, Object>> penaltyChk = jdbcTemplate.queryForList("select penaltyFlag from totaldelay_table where cardNumber = ?", cardNum);
		
		return penaltyChk;
	}
}
