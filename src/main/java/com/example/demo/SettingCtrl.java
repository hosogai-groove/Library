package com.example.demo;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

//設定変更画面用のコントロールクラス
@Controller
public class SettingCtrl {
	
	//設定変更画面の初期表示項目の設定を行う
	@PostMapping("/LibrarySystem_Setting")
	public ModelAndView librarySystemSet() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("SettingScreen");
		StDbConnect sdc = new StDbConnect();

		Cast cast = new Cast();

		try {
			//StDbConnectクラスからgetStDb()で設定用データベースを取得しCastクラスのlistmapCastMapでMap<String, String>型に変換する
			Map<String, String> settingMap = cast.listmapCastMap(sdc.getStDb());

			mv.addObject("settingMap", settingMap);
			
			for(String key:settingMap.keySet()) {
				//画面表示用にデータベースの数値によって表示項目を設定する
				if (key.equals("deadlineCountMethod")) {
					if(settingMap.get(key).equals("0")) {
						mv.addObject("deadlineCountMethod", "土日を含む");
					}
					else if(settingMap.get(key).equals("1")) {
						mv.addObject("deadlineCountMethod", "土日を除く");
					}
				}
				//keyがdeadlineCountMethodでないものはそのままの値を設定する
				else {
					mv.addObject(key, settingMap.get(key));
				}
			}
		//Exception発生時の処理
		}catch(Exception e) {
			e.printStackTrace();
			MsgClass msg = new MsgClass();
			mv.setViewName("LibrarySystemMenu");
			LibSysMenuCtrl lsmc = new LibSysMenuCtrl();
			String setMsg = msg.msg(lsmc.EXCEPTION);

			mv.addObject("setMsg", setMsg);
		}
		return mv;
	} 
}
