package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/except")
@Log4j
public class ExceptionController {
	
	@RequestMapping("/a")
	public void methoda() {
		log.info("a method");
		
		int i = 0;
		int j = 5 / i;
		
		log.info("method done");
	}
	
	@RequestMapping("/b")
	public void methodb() {
		log.info("b method");
		int[] i = new int[3];
		int j = i[3];
		
		log.info("b method done");
	}
	
//	@ExceptionHandler(Exception.class)
//	public void except() {
//		log.info("handle exception....");
//	}
}







