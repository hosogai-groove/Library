package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class StDbConnect {
	private JdbcTemplate jdbcTemplate;
	
	//設定用データベースの全項目を取得する
	@Autowired
	public List<Map<String, Object>> getStDb() throws Exception{
	envFile e = new envFile();
	jdbcTemplate = e.jdbcTemplate();
	List<Map<String, Object>> settingList = jdbcTemplate.queryForList("select * from settingdatabase");
	
	return settingList;
	
	}
	
	
	//設定用データベースを更新する
	@Autowired
	public void updStDb(List<String> updKeyList, Map<String, String> updvalueMapList) throws Exception {
		envFile e = new envFile();
		jdbcTemplate = e.jdbcTemplate();

		for(int i = 0 ; i < updKeyList.size(); i++) {
			jdbcTemplate.update("update settingdatabase set settingValue = ? where settingItem = ?", updvalueMapList.get(updKeyList.get(i)), updKeyList.get(i));
		}

	}
	
	
	//貸出上限数の設定値を取得する
	@Autowired
	public List<Map<String, Object>> getLendingLim() throws Exception{
	envFile e = new envFile();
	jdbcTemplate = e.jdbcTemplate();
	List<Map<String, Object>> lendingLim = jdbcTemplate.queryForList("select settingValue from settingdatabase where settingItem = 'LendingLimit'");
	
	return lendingLim;
	
	}
	
	//返却期限の設定値を取得する
	@Autowired
	public List<Map<String, Object>> getRedate() throws Exception{
	envFile e = new envFile();
	jdbcTemplate = e.jdbcTemplate();
	List<Map<String, Object>> reDeadline = jdbcTemplate.queryForList("select settingValue from settingdatabase where settingItem = 'returnDeadline'");
	return reDeadline;
	
	}	
}
