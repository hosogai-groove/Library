package com.example.demo.LendCfg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

//貸出確認画面用コントローラークラス
@Controller
public class LendCfgCtrl{
	@PostMapping("/LibrarySystem_LendCfg")
	public ModelAndView libSysLendReturn(@ModelAttribute LendCfgForm form){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("LendCfgScreen");
		Object lendList = form.getlendList();
		String cardNumber = form.getcardNumber();
		String userName = form.getuserName();
		String remaining = form.getremaining();
		mv.addObject("lendList", lendList);
		mv.addObject("cardNumber", cardNumber);
		mv.addObject("userName", userName);
		mv.addObject("remaining", remaining);
		return mv;
	}
}
