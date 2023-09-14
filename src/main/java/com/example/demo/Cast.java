package com.example.demo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//データ型の変換を行うクラス
public class Cast {
	//List<Map<?,?>型をObject型で受け取りMap<String, String>型で返すメソッド
	public Map<String, String> listmapCastMap(Object castObj){
		Map<String, String> castResult = new LinkedHashMap<String, String>();
		//replace1～8でMap型にする際に不要となる部分を取り除く
		String replace1 = (String.valueOf(castObj)).replace("[", "");
		String replace2 = replace1.replace("]", "");
		String replace3 = replace2.replace("settingItem=", "");
		String replace4 = replace3.replace("settingValue=", "");
		String replace5 = replace4.replace("{", "");
		String replace6 = replace5.replace("},", ";");
		String replace7 = replace6.replace("}", "");
		String replace8 = replace7.replace(" ", "");
		String[] split1 = replace8.split(";");

		for(int i = 0; i < split1.length; i++) {
			String[] split2 = split1[i].split(",");
			castResult.put(split2[0], split2[1]);
		}

		return castResult;
	}
	//htmlからサーバーに送る際にObject型になったMapをMap<String, String>に変換する
	public Map<String, String> objectCastMap(Object castObj){
		//replace1～2でMap型にする際に不要となる部分を取り除く
		Map<String, String> castResult = new LinkedHashMap<String, String>();
		String replace1 = (String.valueOf(castObj)).replace("{", "");
		String replace2 = replace1.replace("}", "");
		String[] split1 = replace2.split(", ");
		for(int i = 0; i < split1.length; i++) {
			String[] split2 = split1[i].split("=");
			castResult.put(split2[0], split2[1]);
		}
		
		return castResult;
	}
	//htmlからサーバーに送る際にObject型になったListをList<String>に変換する
	public List<String> objectCastList(Object castObj){
		List<String> castResult = new ArrayList<String>();
		//replace1～2でList型にする際に不要となる部分を取り除く
		String replace1 = (String.valueOf(castObj)).replace("[", "");
		String replace2 = replace1.replace("]", "");
		String[] split1 = replace2.split(", ");
		for(int i = 0; i < split1.length; i++) {
			castResult.add(split1[i]);
		}
		
		return castResult;
	}
	
	public Map<String, String> stringCastMap(String castStr){
		Map <String, String> castResult = new LinkedHashMap<String, String>();
		String replace1 = castStr.replace("{", "");
		String replace2 = replace1.replace("}", "");
		String replace3 = replace2.replace("\"", "");
		String[] split1 = replace3.split(",");
		for(int i = 0; i < split1.length; i++){
			String[] split2 = split1[i].split(":");
			castResult.put(split2[0], split2[1]);
		}
		
		return castResult;
	}
	
	
	public int listMapCastInt(Object castObj) {
		int castResult = 0; 
		
		String replace1 = (String.valueOf(castObj)).replace("[","");
		String replace2 = replace1.replace("]", "");
		String replace3 = replace2.replace("{", "");
		String replace4 = replace3.replace("}", "");
		String replace5 = replace4.replace("\"", "");
		String[] split1 = replace5.split("=");
		
		castResult = Integer.parseInt(split1[1]);
		
		return castResult;
	}
	
	public boolean listMapCastbole(Object castObj) {
		boolean castResult = false; 
		
		String replace1 = (String.valueOf(castObj)).replace("[","");
		String replace2 = replace1.replace("]", "");
		String replace3 = replace2.replace("{", "");
		String replace4 = replace3.replace("}", "");
		String replace5 = replace4.replace("\"", "");
		String[] split1 = replace5.split("=");
		
		if (split1[1].equals("true")) {
			castResult = true;
		}

		return castResult;
	}
	
	public List<String> listMapCastList(Object castObj){
		List<String> castResult = new ArrayList<String>();

		String replace1 = (String.valueOf(castObj)).replace("[","");
		String replace2 = replace1.replace("]", "");
		String replace3 = replace2.replace("{", "");
		String replace4 = replace3.replace("}", "");
		String [] split1 = replace4.split(",");
		
		for (int i = 0; i < split1.length; i++) {
			String [] split2 = split1[i].split("=");
			castResult.add(split2[1]);
		}
		return castResult;
	}


}
