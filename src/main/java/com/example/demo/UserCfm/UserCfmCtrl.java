package com.example.demo.UserCfm;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Cast;

//利用者確認画面用コントローラー
@Controller
public class UserCfmCtrl {
	@PostMapping("/LibrarySystem_UserCfm")
	public ModelAndView userCfm(@ModelAttribute UserCfmForm form) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("UserCfmScreen");
		Cast cast = new Cast();
		//利用者の図書カード番号と利用者名を取得する
		Map <String, String> cardDataMap = cast.stringCastMap(form.getcardData());
		String cardNumber = cardDataMap.get("cardNumber");
		String userName = cardDataMap.get("userName");

		mv.addObject("cardNumber", cardNumber);
		mv.addObject("userName", userName);
		
		return mv;
	}
}
