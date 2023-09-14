package com.example.demo.LendReturn;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.LibSysMenuCtrl;
import com.example.demo.MsgClass;

@Controller
public class LendReturnCtrl {
	@PostMapping("/LibrarySystem_LendReturn")
	public ModelAndView libSysLendReturn(@ModelAttribute LendReturnForm form){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("LendReturnScreen");
		String cardNumber = form.getcardNumber();
		String userName = form.getuserName();
		LendReturnModel lrm = new LendReturnModel();
		mv.addObject("cardNumber", cardNumber);
		mv.addObject("userName", userName);

		//利用者の図書カードから貸出や延滞の情報を取得する
		try{
			Map<String, String>	userifmMap = lrm.chkDatabase(cardNumber);
			for(String key : userifmMap.keySet()){
				mv.addObject(key, userifmMap.get(key));
			}
			
		//Exception発生時の処理
		}catch(Exception e){
			e.printStackTrace();
			LibSysMenuCtrl lmc = new LibSysMenuCtrl();
			MsgClass msg = new MsgClass();
			mv.setViewName("LendReturnScreen");
			mv.addObject(lmc.EXCEPTION, msg.msg(lmc.EXCEPTION));
		}
		
		return mv;
	}
}
