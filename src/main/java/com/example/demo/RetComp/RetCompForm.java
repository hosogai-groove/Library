package com.example.demo.RetComp;

public class RetCompForm {
	private String cardNumber;
	private String userName;
	private String bookData;
	
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
	
	public String getbookData(){
		return bookData;
	}
	
	public void setbookData(String bookData){
		this.bookData = bookData;
	}
}
