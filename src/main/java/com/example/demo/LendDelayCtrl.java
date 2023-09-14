package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
//貸出延滞管理テーブル画面用コントローラークラス
@Controller
public class LendDelayCtrl {
	 @PostMapping("/LibrarySystem_LendDelay")
	 public String lenddelay() {		 
		 return "LendDelayScreen";
	 }
}
