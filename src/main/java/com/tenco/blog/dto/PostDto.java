package com.tenco.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {

	private String title;
	private String body;
	private int userId;

	// 응답시 사용하는 속성
	private int id;
	
}
