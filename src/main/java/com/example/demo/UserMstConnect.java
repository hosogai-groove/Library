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
public class UserMstConnect {
	@Autowired	
	private JdbcTemplate jdbcTemplate;
	@ResponseBody
    @PostMapping("/SearchUserList")
	//図書カード番号入力画面に入力された番号から利用者マスタに該当するレコードを検索する
	public List<Map<String, Object>> userMstConnect(@RequestParam Map<String, Object> cardDataMap){
		String cardNum = String.valueOf(cardDataMap.get("key"));
		envFile e = new envFile();
		jdbcTemplate = e.jdbcTemplate();
		//利用者マスタから図書カード番号と利用者名を取得する
		List<Map<String, Object>> userList = jdbcTemplate.queryForList("select cardNumber, userName from userMst where cardNumber = ?", cardNum);
		return userList;
	}
}