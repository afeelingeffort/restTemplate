package com.tenco.blog.controller;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.tenco.blog.dto.PostDto;
import com.tenco.blog.dto.TodoDto;

@RestController
public class ApiController {

	// 1. URI 객체 만들기
	// 2. RestTemplate 객체 만들기
	@GetMapping("/todos/{id}")
	public ResponseEntity<?> restTemplate1(@PathVariable Integer id) {

		// URI uri = new URI("https://jsonplaceholder.typicode.com/todos/1");

		URI uri = UriComponentsBuilder.fromUriString("https://jsonplaceholder.typicode.com").path("/todos")
				.path("/" + id).encode().build().toUri();
		// .queryParam(null, null); // 쿼리 스트링 사용하려면

		System.out.println(uri.toString());

		RestTemplate restTemplate = new RestTemplate();
		// RestTemplate에서 사용할 수 있는 대표적인 메서드 확인
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		System.out.println(response.getStatusCode());
		System.out.println("------------------");
		System.out.println(response.getHeaders());
		System.out.println("------------------");
		System.out.println(response.getBody());

		return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
	}

	// 1. URI 객체 만들기
	// 2. RestTemplate 객체 만들기
	@GetMapping("/todos2/{id}")
	public ResponseEntity<?> restTemplate2(@PathVariable Integer id) {
		URI uri = UriComponentsBuilder.fromUriString("https://jsonplaceholder.typicode.com").path("/todos/" + id)
				.encode().build().toUri();

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<TodoDto> response = restTemplate.getForEntity(uri, TodoDto.class);
		System.out.println(response.getBody());
		System.out.println(response.getBody().getId());
		System.out.println(response.getBody().getTitle());

		return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
	}

	// 클라이언트에서 요청 --> 서버 --> 외부 서버로
	// 1. URI 객체 생성
	// 2. body에 들어갈 데이터 생성
	// RestTemplate 생성 - (post 방식은 추가 작업 필요)
	@GetMapping("/postsTest")
	public ResponseEntity<?> restTemplate3() {
		URI uri = UriComponentsBuilder.fromUriString("https://jsonplaceholder.typicode.com").path("/posts").encode()
				.build().toUri();

		// 바디에 넣을 데이터 만들기
		PostDto postDto = PostDto.builder().title("첫만남이야.").body("여기는 후미진 어느 언덕이야.").userId(10).build();

		RestTemplate restTemplate = new RestTemplate();
		// 인자값 : URI, body에 들어갈 데이터, 파싱될 클래스 타입
		ResponseEntity<PostDto> response = restTemplate.postForEntity(uri, postDto, PostDto.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getHeaders());
		System.out.println(response.getBody());

		return ResponseEntity.status(HttpStatus.CREATED).body(response.getBody());
	}

	// 1. URI 객체 만들기
	// 2. RestTemplate 객체 생성
	@GetMapping("/exchangeTest")
	public ResponseEntity<?> restTemplate4() {
		// exchange 메서드 사용방법
		// HttpEntity 클래스 (body, headers) 생성할 수 있다.
		URI uri = UriComponentsBuilder.fromUriString("https://jsonplaceholder.typicode.com").path("/posts").encode()
				.build().toUri();

		RestTemplate restTemplate = new RestTemplate();
		// -1. HttpHeaders 만들기
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-type", "application/json; charset=UTF-8");
		// httpHeaders.add("key", "value");

		// -2. body에 들어갈 key = value 구조 만들기 (데이터 만들기)
		// HashMap, MultiValueMap -> key=value 구조
		// fruit : "바나나", -> HashMap은 덮어쓰기 됨.
		// fruit : ["바나나", "오렌지", "사과"] Multivaluemap -> 배열 구조로 들어감.
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("title", "반가워");
		params.add("body", "그늘진 어느 언덕 아래");
		params.add("userId", "100");

		// -3. HttpEntity 생성해서 결합하기
		// 인자값 : body 데이터, HttpHeader
		HttpEntity<MultiValueMap<String, String>> reqEntity = new HttpEntity<>(params, httpHeaders);

		// URI 객체, METHOD, HttpEntity, returnType
		ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, reqEntity, String.class);
		System.out.println(response.getBody());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response.getBody());
	}

}
