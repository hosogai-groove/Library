package com.example.demo.LendSystem;

public class LendSystemForm {
	private String remaining;
	private String cardNumber;
	private String userName;

	public String getremaining(){
		return remaining;
	}
	
	public void setremaining(String remaining){
		this.remaining = remaining;
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
}
