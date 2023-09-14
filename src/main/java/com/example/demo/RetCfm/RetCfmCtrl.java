package com.example.demo.RetCfm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
//返却確定画面のコントローラー
public class RetCfmCtrl {
	@PostMapping("/LibrarySystem_RetCfm")
	public ModelAndView retCfmSystem(@ModelAttribute RetCfmForm form){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("RetCfmScreen");
		String cardNumber = form.getcardNumber();
		String userName = form.getuserName();
		String returnData = form.getreturnData();
		mv.addObject("cardNumber", cardNumber);
		mv.addObject("userName", userName);
		mv.addObject("returnData", returnData);

		return mv;
	} 
}
