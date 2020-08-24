package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;

@Controller		// Component라고 안쓰고 Controller라고 써도 Bean 생성(Controller가 Component 포함).
/* @RequestMapping("/my/*") */
@Log4j
public class MyController {

	@RequestMapping("")
	public void myHandler1() {
		log.info("my controller is working......");
	}
	
	@RequestMapping("/a")
	public void myHandler2() {
		log.info("myHandler2 is working......");
	}
	
	@RequestMapping("/b")
	public void myHandler3() {
		log.info("myHandler3 is working......");
	}
	
	@RequestMapping(value = "/c", method = RequestMethod.GET)	// GET 방식
	public void myHandler4() {
		log.info("myHandler4.......");
	}
	
	@RequestMapping(value = "/c", method = RequestMethod.POST)	// POST 방식
	public void myHandler5() {
		log.info("myHandler5.......");
	}
	
	@GetMapping("/d")
	public void myHandler6() {
		log.info("myHandler6.........");
	}
	
	@PostMapping("/d")
	public void myHandler7() {
		log.info("myHandler7.........");
	}
	
}
