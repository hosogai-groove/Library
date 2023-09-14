package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//貸出書籍検索ajax接続用クラス
public class LendSysConnect {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ResponseBody
    @PostMapping("/LendingBook")	
	public List<Map<String, Object>> lendSysConnect(@RequestParam Map<String, Object> lendDataMap){
		envFile e = new envFile();
		jdbcTemplate = e.jdbcTemplate();
		String bookNum = String.valueOf(lendDataMap.get("bookNum"));
		String bookBNum = String.valueOf(lendDataMap.get("bookBNum"));
		List<Map<String, Object>> bookList = new ArrayList<Map<String, Object>>();
		if(bookBNum == "") {
			//書籍枝番号に入力がない場合の検索方法
			bookList = jdbcTemplate.queryForList("select bm.bookNumber, bm.bookName, bm.bookBranchNumber from bookmst bm where bm.bookNumber Like ? and (bm.bookNumber, bm.bookBranchNumber) not in (select bookNumber, bookBranchNumber from lendingstatus_table)", bookNum + "%");
		}
		
		else {
			//書籍枝番号に入力がある場合の検索方法
			bookList = jdbcTemplate.queryForList("select bm.bookNumber, bm.bookName, bm.bookBranchNumber from bookmst bm where bookNumber Like ? and bookBranchNumber = ? and (bm.bookNumber, bm.bookBranchNumber) not in (select bookNumber, bookBranchNumber from lendingstatus_table)", bookNum + "%", bookBNum);
		}
		return bookList;
	}
}
