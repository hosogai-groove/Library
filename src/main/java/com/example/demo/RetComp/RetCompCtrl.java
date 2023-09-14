package com.example.demo.RetComp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RetCompCtrl {
	@PostMapping("/LibrarySystem_RetComp")
	public ModelAndView retCfmSystem(@ModelAttribute RetCompForm form){
		ModelAndView mv = new ModelAndView();
		RetCompModel rcm = new RetCompModel();
		mv.setViewName("RetCompScreen");
		String cardNumber = form.getcardNumber();
		String userName = form.getuserName();
		String bookData = form.getbookData();
		String msg = rcm.delLendData(bookData);
		
		mv.addObject("cardNumber", cardNumber);
		mv.addObject("userName", userName);
		mv.addObject("text", msg);

		return mv;
	} 
}
