package com.example.demo.Return;

public class ReturnForm {
	private String cardNumber;
	private String userName;
	
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
}
