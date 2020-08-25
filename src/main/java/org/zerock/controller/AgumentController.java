package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.CustomMemberEditor;
import org.zerock.domain.Member;
import org.zerock.domain.MemberList;

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
	
	// 배열
	@RequestMapping("/j")
	public void method10(@RequestParam String[] name) {
		log.info("j method");
		log.info(name.length);
		
		for (String n : name) {
			log.info(n);
		}
	}
	
	// List
	@RequestMapping("/k")
	public void method11(@RequestParam("n") List<String> name) {	// 쿼리 스트링에 name을 쓰지 않고 n을 써도 된다.
		log.info("k method");
		log.info(name.size());
		
		for (String n : name) {
			log.info(n);
		}
	}
	
	// 객체 리스트
	@RequestMapping("/l")
	public void method12(MemberList member) {
		log.info("l method");
		log.info(member);
	}
	
	// String Type -> Date Type
	@RequestMapping("/m")
	public void method13(@RequestParam("date") Date date) {
		log.info("m method");
		log.info(date);
	}
	
	// Request Parameter를 적절한 텍스트로 변환
	@RequestMapping("/n")
	public void method14(@RequestParam("mem") Member member) {
		log.info("m method");
		log.info(member);
	}
	
	// @InitBinder : RequestParam의 파라미터를 변환해서 처리.
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format, false));
		
		// MemberEditor
		binder.registerCustomEditor(Member.class, new CustomMemberEditor());
	}
	
	// Model
	@RequestMapping("/o")
	public void method15(Model model) {
		log.info("o method");
		log.info(model);
		
		model.addAttribute("my-attr", "my-value");
		
		log.info(model);
	}
	
	@RequestMapping("/p")
	public void method16(@ModelAttribute("str") String str, Model model) {
		log.info("p method");
		log.info(model);
		
		//model.addAttribute("attr", service.get(str));
		
		// Parameter의 값을 Model 집어 넣는다.
		//model.addAttribute("str", str);
		
		log.info(model);
	}
	
	// 이름을 생략할 경우
	@RequestMapping("/q")
	public void method17(Model model) {
		log.info("q method");
		model.addAttribute("str", "str-value");
		model.addAttribute("str-value2");
		model.addAttribute(new Member());
		model.addAttribute(new String[] {"a", "b"});
		//model.addAttribute(new ArrayList<Member>());	// Collection 중에 비어있는 것은 등록이 안된다.
		
		List<Member> mlist = new ArrayList<Member>();
		mlist.add(new Member());
		model.addAttribute(mlist);
		
		log.info(model);
	}
	
	// 기본 타입이 아닌 경우(@ModelAttribute를 생략 가능)
	@RequestMapping("/r")
	public void method18(Member member, Model model) {
		log.info("r method");
		log.info(model);
	}
	
}

