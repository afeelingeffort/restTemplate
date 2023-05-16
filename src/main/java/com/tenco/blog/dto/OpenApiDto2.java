package com.tenco.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OpenApiDto2 {

	private Integer currentCount;
	private Datadt2[] data;
//	private Integer numOfRows;
//	private Integer pageNo;
//	private Integer resultCode;
//	private String resultMsg;
//	private Integer totalCount;
	
}
