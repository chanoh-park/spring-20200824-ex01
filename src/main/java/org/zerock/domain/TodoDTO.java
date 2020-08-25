package org.zerock.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TodoDTO {
	private String title;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")	// 작성한 pattern의 문자열 형식이 맞으면 자동으로 날짜 타입으로 변환(@InitBinder 필요 없음).
	private Date dueDate;
}
