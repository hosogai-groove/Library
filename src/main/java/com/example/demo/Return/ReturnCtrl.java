package com.example.demo.Return;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
//返却書籍選択用コントローラー
@Controller
public class ReturnCtrl {
	@PostMapping("/LibrarySystem_ReturnSystem")
	public ModelAndView retrunSystem(@ModelAttribute ReturnForm form){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ReturnScreen");
		String cardNumber = form.getcardNumber();
		String userName = form.getuserName();
		ReturnModel rm = new ReturnModel();
		//利用者の貸出書籍情報を文字列で取得する
		String dataString = rm.getBookData(cardNumber);
		mv.addObject("cardNumber", cardNumber);
		mv.addObject("userName", userName);
		mv.addObject("dataString", dataString);
		return mv;
	}
}
