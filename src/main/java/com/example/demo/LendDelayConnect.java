package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class LendDelayConnect {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void dalaydel(String bookNum, String bookBNum) throws Exception{
		envFile e = new envFile();
		jdbcTemplate = e.jdbcTemplate();
		jdbcTemplate.update("delete from lenddelay_table where bookNumber = ? and bookBranchNumber = ?", bookNum, bookBNum);
	}
	@ResponseBody
    @PostMapping("/LendDelayList")	
	public List<Map<String, Object>> showList (@RequestParam Map<String, Object> leDelayDataMap){
	//public String showList(@RequestParam Map<String, Object> leDelayDataMap){
		envFile e = new envFile();
		jdbcTemplate = e.jdbcTemplate();
		String fOrder = String.valueOf(leDelayDataMap.get("fOrder"));
		String fOrderVal = String.valueOf(leDelayDataMap.get("fOrderVal"));
		String sOrder = String.valueOf(leDelayDataMap.get("sOrder"));
		String sOrderVal = String.valueOf(leDelayDataMap.get("sOrderVal"));		
		String tOrder = String.valueOf(leDelayDataMap.get("tOrder"));
		String tOrderVal = String.valueOf(leDelayDataMap.get("tOrderVal"));
		String dateData = String.valueOf(leDelayDataMap.get("dateData"));
		String dateVal = String.valueOf(leDelayDataMap.get("dateVal"));
			
		List <Map<String, Object>> lendDelayList = jdbcTemplate.queryForList("SELECT l.deadLineDate, l.cardNumber, u.userName, l.bookNumber, \n"
				+ "b.bookName, l.bookBranchNumber, g.genreName, l.mailFlag \n"
				+ "FROM lenddelay_table l inner join bookmst b\n"
				+ "on l.bookNumber = b.bookNumber\n"
				+ "inner join usermst u\n"
				+ "on l.cardNumber  = u.cardNumber\n"
				+ "inner join genremst g\n"
				+ "on b.genreCord = g.genreCord\n"
				+ "where l.deadLineDate "+dateVal+" ?"
				+ "group by l.bookNumber, l.bookBranchNumber\n"
				+ "order by "+fOrder+" "+fOrderVal+", "+sOrder+" "+sOrderVal+", "+tOrder+" "+tOrderVal+"", dateData
		);
		
		return lendDelayList;
		//return dataStr.substring(0, dataStr.length() - 2);
	}
}
