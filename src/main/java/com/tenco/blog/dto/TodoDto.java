package com.tenco.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoDto {

	private int userId;
	private int id;
	private String title;
	private boolean completed;
	
}
