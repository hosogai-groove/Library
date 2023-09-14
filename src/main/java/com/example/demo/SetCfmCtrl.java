package com.example.demo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


//設定確認画面のコントロールクラス
@Controller
public class SetCfmCtrl {

	private final int DLCOUNT_NUM = 0;
	private final int DLCOUNTM_NUM = 1;
	private final int DLCOUNTT_NUM = 2;
	private final int LENDLIM_NUM = 3;
	private final int MAILSENDT_NUM = 4;
	private final int PENADAYS_NUM = 5;
	private final int PENAPERIOD_NUM = 6;
	private final int DEADLINE_NUM = 7;
	private final int RESPERIOD_NUM = 8;
	private final int URGEMAIL_NUM = 9;
	
	public final int KEY_LIST = 0;
	public final int CHK_MAP = 1;
	public final int UPD_MAP = 2;

	//設定確認画面の表示項目の設定を行う
	@PostMapping("/LibrarySystem_Set_Cfm")
	public ModelAndView libSysSetCfm(@ModelAttribute SetCfmForm form) {		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("SetCfmScreen");
		String [] settingValue = new String[10];

		//getdlCountvalue()で取得した文字列によって配列に登録する値を変更する
		if((form.getdlCountvalue()).equals("土日を含む")) {
			settingValue[DLCOUNT_NUM] = "0";
		}
		else if((form.getdlCountvalue()).equals("土日を除く")) {
			settingValue[DLCOUNT_NUM] = "1";
		}
		
		//各項目を配列に登録する
		settingValue[LENDLIM_NUM] = form.getlendLimvalue();
		settingValue[DEADLINE_NUM] = form.getdeadlinevalue();
		settingValue[DLCOUNTT_NUM] = form.getdlCountTvalue();
		settingValue[DLCOUNTM_NUM] = form.getdlCountMvalue();
		settingValue[PENADAYS_NUM] = form.getpenaDaysvalue();
		settingValue[PENAPERIOD_NUM] = form.getpenaPeriodvalue();
		settingValue[RESPERIOD_NUM] = form.getresPeriodvalue();
		settingValue[URGEMAIL_NUM] = form.geturgeMailvalue();
		settingValue[MAILSENDT_NUM] = form.getmailSendTvalue();

		
		Cast cast = new Cast();
		//画面から取得した変更前の設定用データベースの値をMap型に変換する
		Map<String, String> settingMap = cast.objectCastMap(form.getbfSettingList());
		String valueList [] = {"dlCountvalue", "dlCountMvalue", "dlCountTvalue", "lendLimvalue", "mailSendTvalue", "penaDaysvalue", "penaPeriodvalue", "deadlinevalue", "resPeriodvalue", "urgeMailvalue"};
		
		Map<String, String> updateMap = new LinkedHashMap<String, String>();
		List<String> chgKeyList = new ArrayList<>();
		
		
		//設定変更後のそれぞれの項目と値をMapで作成する
		int i = 0;
		for(String key: settingMap.keySet()) {
			updateMap.put(key, settingValue[i]);
			
			//現在の設定内容と入力内容が異なる場合そのsettingMapのkeyの値をchgKeyListに登録する
			if(!(settingMap.get(key)).equals(settingValue[i])) {
				chgKeyList.add(key);
			}
			
			//valueList[i]が"dlCountvalue"のときsettingValue[i]の値によって表示する文字列を変更する
			if(valueList[i].equals("dlCountvalue")) {
				if(settingValue[i].equals("0")) {
					mv.addObject(valueList[i], "土日を含む");
				}
				else if(settingValue[i].equals("1")) {
					mv.addObject(valueList[i], "土日を除く");
				}
			}
			
			//keyがdlCountvalueでないものはsettingValue[i]を値として設定する
			else {
				mv.addObject(valueList[i], settingValue[i]);
			}
			i++;
		}
		
		mv.addObject("settingMap", settingMap);
		mv.addObject("updMap", updateMap);
		mv.addObject("chgKeyList", chgKeyList);

		return mv;
		
	}

	//設定用データベースの更新を行い図書館システムメインメニューに画面遷移する
	@PostMapping("/LibrarySystem_MainMenu_Msg")
	public ModelAndView settingUpdCtrl(@ModelAttribute SetCfmForm form) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("LibSysMenu");
		SetCfmModel scm = new SetCfmModel();
		Object[] setObj = new Object[3] ;
		
		setObj[KEY_LIST] = form.getchgKeyList();
		setObj[CHK_MAP] = form.getsettingMap();
		setObj[UPD_MAP] = form.getupdMap();

		//SettingCfmModelクラスにObject[] setObjを引数として渡し、戻り値でメッセージを受け取る
		String setMsg = scm.updSetting(setObj);

		mv.addObject("setMsg", setMsg);

		return mv;

	}
}
