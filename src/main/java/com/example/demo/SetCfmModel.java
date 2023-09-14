package com.example.demo;

import java.util.List;
import java.util.Map;

//設定用データベースの更新を行うクラス
public class SetCfmModel {
	
	private final String SET_OK = "setOK";
	private final String SET_NG = "setNG";
	
	//設定用データベースの更新を行う
	public String updSetting(Object[]  setObj) {
		String setMsg = null;
		MsgClass msg = new MsgClass();
		try {
			StDbConnect sdc = new StDbConnect();
			Cast cast = new Cast();

			SetCfmCtrl scc = new SetCfmCtrl();

			//StDbConnectクラスからgetStDb()で設定用データベースを取得しCastクラスでMap型に変換する
			Map<String, String>chkMap = cast.listmapCastMap(sdc.getStDb());

			//設定用データベースの更新を行う個所の項目名の内容をList型に変換する
			List<String> chgKeyList = cast.objectCastList(setObj[scc.KEY_LIST]);

			//設定変更前の設定用データベースの内容をCastクラスでMap型に変換する
			Map<String, String> bfchkMap = cast.objectCastMap(setObj[scc.CHK_MAP]);

			//設定変更予定の設定用データベースの内容をCastクラスでMap型に変換する
			Map<String, String> updateMap = cast.objectCastMap(setObj[scc.UPD_MAP]);

			//更新元のデータに変更がないかを確認する
			if(bfchkMap.equals(chkMap)) {
				//更新する項目と値を引数に設定し更新を行う
				sdc.updStDb(chgKeyList, updateMap);
				//更新元のデータに変更がなく、更新が成功した場合のメッセージを設定する
				setMsg = msg.msg(SET_OK);
			}
			else {
				//更新元のデータに変更があった場合のメッセージを設定する
				setMsg = msg.msg(SET_NG);
			}
			
		//Exceptiom発生時の処理
		}catch(Exception e) {
			e.printStackTrace();
			LibSysMenuCtrl lsmc = new LibSysMenuCtrl();
			//Exceptiom発生時のメッセージを設定する
			setMsg = msg.msg(lsmc.EXCEPTION);
		}
		return setMsg;
	}
}
