package com.example.demo.LendSystem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

//貸出書籍選択用コントローラークラス
@Controller
public class LendSystemCtrl {
	@PostMapping("/LibrarySystem_LendSystem")
	public ModelAndView lendSystem(@ModelAttribute LendSystemForm form){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("LendSystemScreen");
		String remaining = form.getremaining();
		String cardNumber = form.getcardNumber();
		String userName = form.getuserName();
		
		mv.addObject("remaining", remaining);
		mv.addObject("cardNumber", cardNumber);
		mv.addObject("userName", userName);
		return mv;
	}
}
