package org.zerock.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.Member;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/arg/*")
@Log4j
public class AgumentController {
	
	// ex) /arg/a?name=john
	@RequestMapping("/a")
	public void method1(HttpServletRequest req) {	// public void method1(Argument) { } : Argument는 굉장히 유연하게 쓸 수 있다.
		log.info(req);
		log.info(req.getParameter("name"));
	}
	
	@RequestMapping("/b")
	public void method2(@RequestParam("name") String name) {
		log.info(name);
	}
	
	@RequestMapping("/c")
	public void method3(@RequestParam String name) {	// @RequestParam의 변수명과 타입의 변수명이 같으면 Param의 값 생략 가능.
		log.info("c method");
		log.info(name);
	}
	
	@RequestMapping("/d")
	public void method4(String name) {	// @RequestParam 생략 가능.
		log.info("d method");
		log.info(name);
	}
	
	// ex) /e?name=jane&age=100
	@RequestMapping("/e")
	public void method5(HttpServletRequest req) {
		log.info("e method");
		log.info(req.getParameter("name"));
		log.info(req.getParameter("age"));
	}
	
	@RequestMapping("/f")
	public void method6(String name, String age) {
		log.info("f method");
		log.info(name);
		log.info(age);
	}
	
	@RequestMapping("/g")
	public void method7(String name, int age) {		// 형변환도 자동으로 해준다.
		log.info("g method");
		log.info(name);
		log.info(age);
	}
	
	// Java Bean 사용
	@RequestMapping("/h")
	public void method8(String name, int age) {
		log.info("h method");
		
		Member member = new Member();
		member.setName(name);
		member.setAge(age);
		
		log.info(member);
	}
	
	@RequestMapping("/i")
	public void method9(Member member) {
		log.info("i method");
		log.info(member);
	}
	
}
