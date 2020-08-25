package org.zerock.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.Member;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/ret")
@Log4j
public class ReturnController {
	
	// return type : void (요청받은 jsp에서 일을 한다)
	@RequestMapping("/a")
	public void methoda() {
		log.info("a method");
	}
	
	// return type : String
	@RequestMapping("/b")
	public String methodb() {
		log.info("b method");
		
		return "/ret/a";	// 기존에 존재하는 jsp 파일로 포워드 하고 싶을 경우
	}
	
	@RequestMapping("/c")
	@ResponseBody	// return 된 값 자체가 몸통이 된다( = return 값의 응답을 받는다)
	public String methodc() {
		log.info("c method");
		
		return "hello world";
	}
	
	// Model 사용
	@RequestMapping("/d")
	public String methodd(Model model) {
		log.info("d method");
		
		model.addAttribute("myAttr", "my-val");
		
		return "/ret/a";
	}
	
	// 객체 사용
	@RequestMapping("/e")
	public String methode(Model model) {
		log.info("e method");
		
		Member member = new Member();
		member.setName("john");
		member.setAge(999);
		
		model.addAttribute("mem", member);
		
		return "/ret/a";
	}
	
	@RequestMapping("/f")
	public String methodf(Model model) {
		log.info("f method");
		Member member = new Member();
		member.setName("seoul");
		member.setAge(1000);
		model.addAttribute(member);
		
		return "/ret/a";
	}
	
	// 배열
	@RequestMapping("/g")
	public String methodg(Model model) {
		log.info("g method");
		
		String[] strs = new String[]{"seoul", "jeju", "korea"};
		model.addAttribute("cities", strs);
		
		return "/ret/b";
	}
	
	@RequestMapping("/h")
	public String methodh(Model model) {
		log.info("h method");
		
		Member m1 = new Member();
		m1.setName("seoul");
		m1.setAge(22);
		
		Member m2 = new Member();
		m2.setName("korea");
		m2.setAge(33);
		
		/* 1. 배열
		Member[] members = new Member[] {m1, m2};
		model.addAttribute("memberList", members); or model.addAttribute(members);	-> 배열은 이름을 안넣으면 이름 뒤에 자동으로 List가 붙는다
		*/
		
		// 2. List
		List<Member> list = new ArrayList<Member>();
		list.add(m1);
		list.add(m2);
		model.addAttribute("memberList", list);	// model.addAttribute(list);	-> List type은 이름을 안넣으면 이름 뒤에 자동으로 List가 붙는다
		
		return "/ret/b";
	}
	
	// @ModelAttribute
	@RequestMapping("/i")
	public String methodi(@ModelAttribute("name") String name) {
		log.info("i method");
		log.info(name);
		
		return "/ret/c";
	}
	
	@RequestMapping("/j")
	public String methodj(Member member) {
		log.info("j method");
		
		return "/ret/c";
	}
	
	@RequestMapping("/k")
	public String methodk(@ModelAttribute("mem") Member member) {
		log.info("k method");
		
		return "/ret/c";
	}
}
