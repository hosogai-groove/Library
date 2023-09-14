package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
//延滞一覧画面用コントローラークラス
@Controller
public class DelListCtrl {
	 @PostMapping("/LibrarySystem_DelList")
	 public String delaySystem() {		 
		 return "DelListScreen";
	 }	 
}
