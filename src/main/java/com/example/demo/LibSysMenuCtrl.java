package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LibSysMenuCtrl {
	
	public final String EXCEPTION = "Exception";
	
	
	 @GetMapping("/LibrarySystem_MainMenu")
	 public String librarySystemMain() {
		 return "LibSysMenu";
	 }
	 
	 @PostMapping("/LibrarySystem_MainMenu")
	 public String librarySystemMainReturn() {		 
		 return "LibSysMenu";
	 }
	 
//	 @PostMapping("/LibrarySystem_CardNumInsert")
//	 public String cardnumberInsert() {		 
//		 return "CardNumInsert";
//	 }
//	 
	 
}