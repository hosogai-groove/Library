package com.example.demo;

public class MsgClass {

	public String msg(String msgStr) {
		String msg = null;
		
		//貸出上限数に達しているときのメッセージ
		if(msgStr.equals("LendLimit")) {
			msg = "貸出上限数に達しているため新たに貸出を行うことができません";
		}
		
		//利用者に延滞している書籍があるときのメッセージ
		else if(msgStr.equals("DelayReturn")) {
			msg = "書籍の延滞があるため貸出を受け付けることができません";
		}
		
		//利用者に貸出ペナルティがかかっているときのメッセージ
		else if (msgStr.equals("PenaltyFlag")) {
			msg = "貸出制限がかかっています";
		}
		
		//利用者に貸出している書籍がなく返却が行えないときのメッセージ
		else if (msgStr.equals("NotLend")) {
			msg = "貸出が行われていないので返却は選択できません";
		}
		
		//貸出処理の際に貸出状況テーブルに貸し出し予定の書籍が存在した時のメッセージ
		else if (msgStr.equals("errorlend")) {
			msg = "入力された図書は貸出中のため\n" + "貸出処理を行うことができませんでした";
		}
		
		//貸出処理が正常に終了した時のメッセージ
		else if (msgStr.equals("lend")) {
			msg = "貸出処理が正常に終了しました";
		}
		
		//返却処理時に貸出状況テーブルに返却対象がなかった時のメッセージ
		else if (msgStr.equals("AlreadyRet")) {
			msg = "すでに返却されています";
		}
		
		
		//返却処理が正常に終了した時のメッセージ
		else if (msgStr.equals("ReturnMsg")) {
			msg = "返却が完了しました";
		}
		
		
		//設定用データベース変更時のメッセージ
		else if(msgStr.equals("setOK")) {
			msg = "設定が変更されました";
		}
		//設定用データベース変更時に変更前の状態を確認できなかった時のメッセージ
		else if(msgStr.equals("setNG")) {
			msg = "前回の設定状態を確認できませんでした";
		}
		
		
		//Exceptionが発生した時のメッセージ
		else if(msgStr.equals("Exception")) {
			msg = "予期せぬエラーが発生しました。\n"
					+ "開発担当者に連絡してください。";
		}
		//貸出処理中にExceptionが発生した時のメッセージ
		else if(msgStr.equals("LendException")) {
			msg = "貸出処理中に予期せぬエラーが発生したため貸出が正常に終了しませんでした。\n"
					+ "開発担当者に連絡してください。";
		}
		
		//返却処理時にExceptionが発生した時のメッセージ
		else if(msgStr.equals("RetException")) {
			msg = "返却処理中に予期せぬエラーが発生したため返却が正常に行えませんでした。\n"
					+ "開発担当者に連絡してください。";
		}
		
		return msg;
	}
}
