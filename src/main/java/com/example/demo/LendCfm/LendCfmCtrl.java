package com.example.demo.LendCfm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.LendStateConnect;
import com.example.demo.MsgClass;
import com.example.demo.StDbConnect;

//貸出完了画面のコントロールクラス
@Controller
public class LendCfmCtrl{
	@PostMapping("/LibrarySystem_LendCfm")
	public ModelAndView lendCfm(@ModelAttribute LendCfmForm form){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("LendCfmScreen");
		String lendData = form.getlendRecodeList();
		String cardNumber = form.getcardNumber();
		String userName = form.getuserName();
		LendCfmModel lcmm = new LendCfmModel(); 
		MsgClass msg = new MsgClass();
		//貸出処理結果のメッセージをを受け取る
		String lendmsg =  lcmm.lendRgs(lendData, cardNumber);
		StDbConnect sdc = new StDbConnect();
		LendStateConnect lsc = new LendStateConnect();
		int lendLim = 0;
		int lendNum = 0;
		
		//利用者の貸出可能数を検索する
		try {
			lendLim = Integer.parseInt((sdc.getLendingLim().get(0).get("settingValue")).toString());
			lendNum = Integer.parseInt(lsc.getCount(cardNumber).get(0).get("count(*)").toString());			

		} catch (Exception e) {
			e.printStackTrace();
			lendmsg = msg.msg(lcmm.LEND_EXCEPTION);
		}
		//利用者の貸出可能数を計算する
		int remaining = lendLim - lendNum;
		
		mv.addObject("text", lendmsg);		
		mv.addObject("cardNumber", cardNumber);
		mv.addObject("remaining", remaining);
		mv.addObject("userName", userName);
		return mv;
	}
}
