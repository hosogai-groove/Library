package com.example.demo.LendCfg;

public class LendCfgForm {
	private Object lendList;
	private String cardNumber;
	private String userName;
	private String remaining;
	
	
	public Object getlendList(){
		return lendList;
	}
	
	public void setlendList(Object lendList){
		this.lendList = lendList;
	}
	
	public String getcardNumber(){
		return cardNumber;
	}
	
	public void setcardNumber(String cardNumber){
		this.cardNumber = cardNumber;
	}
	
	public String getuserName(){
		return userName;
	}
	
	public void setuserName(String userName){
		this.userName = userName;
	}
	
	public String getremaining(){
		return remaining;
	}
	
	public void setremaining(String remaining){
		this.remaining = remaining;
	}
}
