package com.example.demo.RetCfm;

public class RetCfmForm {
	private String cardNumber;
	private String userName;
	private String returnData;
	
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
	
	
	public String getreturnData(){
		return returnData;
	}
	
	public void setreturnData(String returnData){
		this.returnData = returnData;
	}
}
