package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	@RequestMapping("")
	public void basic() {
		log.info("basic...................");
	}
	
	@RequestMapping(value="/basic", method = {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		log.info("basic get.............");
	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get ..................");
	}
	
	// 131 page
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, 
			@RequestParam("age") int age) {
		log.info("name:" + name);
		log.info("age:" + age);
		
		return "ex02";
		
	}
	
	// 130 page
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info("" + dto);
		return "ex01";
	}
	
	// 132 page
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
		log.info("ids: " + ids);
		
		return "ex02List";
	}
	// 133 page
	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids") String[] ids) {
		log.info("array ids: " + Arrays.toString(ids));
		return "ex02Array";
	}
	
	// 134 page
	// /sample/ex02Bean?list%5B0%5D.name=aaa&list%5B1%5D.name=bbb&list%5B2%5D.name=ccc
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos: " + list);
		
		return "ex02Bean";
	}
	
	// 135 ~ 137 page
	// 2개의 메소드, 하나의 클래스
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo: " + todo);
		return "ex03";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format, false));
	}
	
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("/exUpload......");
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(List<MultipartFile> files) {
		log.info("/exuploadpost ..");

		/*
		for (MultipartFile file : files) {
			log.info("----------");
			log.info("name:" + file.getOriginalFilename());
			log.info("size:" + file.getSize());
		}
		*/
		
		// lambda
		files.forEach(file -> {
			log.info("----------");
			log.info("name:" + file.getOriginalFilename());
			log.info("size:" + file.getSize());
		});
		
		// 1. class 작성
		// 2. instance 생성
		// 3. argument 전달
		MyConsumer consumer = new MyConsumer();
		files.forEach(consumer);
		
		// 1. 무명클래스(anonymous class)의 인스턴스생성 및 전달
		files.forEach(new Consumer<MultipartFile>() {
			@Override
			public void accept(MultipartFile file) {
				log.info("----------");
				log.info("name:" + file.getOriginalFilename());
				log.info("size:" + file.getSize());
			}
		});
		
		// 1. lambda로 작성
		files.forEach(file -> {
			log.info("----------");
			log.info("name:" + file.getOriginalFilename());
			log.info("size:" + file.getSize());
		});
	}
}

@Log4j
class MyConsumer implements Consumer<MultipartFile> {
	@Override
	public void accept(MultipartFile file) {
		log.info("----------");
		log.info("name:" + file.getOriginalFilename());
		log.info("size:" + file.getSize());
	}
}








