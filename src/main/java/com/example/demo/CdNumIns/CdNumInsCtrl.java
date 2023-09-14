package com.example.demo.CdNumIns;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class CdNumInsCtrl {
	 @PostMapping("/LibrarySystem_CardNumInsert")
	 public String cardnumberInsert() {		 
		 return "CdNumInsScreen";
	 }
}
