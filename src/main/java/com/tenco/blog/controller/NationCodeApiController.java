package com.tenco.blog.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.tenco.blog.dto.DataDt;
import com.tenco.blog.dto.OpenApiDto;
import com.tenco.blog.dto.OpenApiDto2;

@RestController
public class NationCodeApiController {

	@GetMapping("/nationCode1")
	public ResponseEntity<?> nationCode1() {

		// HttpClient
		// Retrofit2
		// HTTP OK
		// RestTemplate으로 변환하기

		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/1262000/CountryCodeService2/getCountryCodeList2"); /* URL */
		StringBuilder sb = new StringBuilder();

		try {
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
					+ "=Cuo5MmMb2QEiC58RNfbyKZ3q7MF%2F5mvNSC%2FXcSI%2F9mkEK8Blx2zD5dULoP9UK0MaSi049JL%2BUmo7K%2FHXgEH9dQ%3D%3D");
			urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "="
					+ URLEncoder.encode("JSON", "UTF-8")); /* XML 또는 JSON */
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
					+ URLEncoder.encode("50", "UTF-8")); /* 한 페이지 결과 수 */
			urlBuilder.append(
					"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("2", "UTF-8")); /* 페이지 번호 */
//	        urlBuilder.append("&" + URLEncoder.encode("cond[country_nm::EQ]","UTF-8") + "=" + URLEncoder.encode("가나", "UTF-8")); /*한글 국가명*/
//	        urlBuilder.append("&" + URLEncoder.encode("cond[country_iso_alp2::EQ]","UTF-8") + "=" + URLEncoder.encode("GH", "UTF-8")); /*ISO 2자리코드*/

			URL url = new URL(urlBuilder.toString());

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();
			System.out.println(sb.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.OK).body(sb.toString());
	}

	@GetMapping("/nationCode2")
	public ResponseEntity<?> nationCode2() {

		// 1. 연습 문제 - RestTemplate로 변환해서 코드 사용하기
		// 2. 연습 문제 - RestTemplate로 사용해서 공공데이터 받아오기
		// 3. 연습 문제 - 공공 데이터 DB 넣어보기
		HttpEntity<OpenApiDto> response = null;

		try {
			URI uri = UriComponentsBuilder
					.fromUriString("http://apis.data.go.kr/1262000/CountryCodeService2/getCountryCodeList2").encode()
					.build().toUri();

			StringBuilder urlBuilder = new StringBuilder(
					"http://apis.data.go.kr/1262000/CountryCodeService2/getCountryCodeList2"); /* URL */

			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
					+ "=Cuo5MmMb2QEiC58RNfbyKZ3q7MF%2F5mvNSC%2FXcSI%2F9mkEK8Blx2zD5dULoP9UK0MaSi049JL%2BUmo7K%2FHXgEH9dQ%3D%3D");
			urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "="
					+ URLEncoder.encode("JSON", "UTF-8")); /* XML 또는 JSON */
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
					+ URLEncoder.encode("50", "UTF-8")); /* 한 페이지 결과 수 */
			urlBuilder.append(
					"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("2", "UTF-8")); /* 페이지 번호 */

			uri = new URI(urlBuilder.toString());
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);

			response = restTemplate.getForEntity(uri, OpenApiDto.class);
			System.out.println(response.getBody().getData());

			for (DataDt dt : response.getBody().getData()) {
				System.out.println(dt.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
	}
	
	@GetMapping("/nationCode3")
	public ResponseEntity<?> nationCode3(){
		HttpEntity<OpenApiDto2> response = null;
		try {
			URI uri = UriComponentsBuilder
					.fromUriString("https://apis.data.go.kr/1262000/TravelAlarmService2/getTravelAlarmList2")
					.encode()
					.build()
					.toUri();
			
			StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/1262000/TravelAlarmService2/getTravelAlarmList2");
			
			urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=Cuo5MmMb2QEiC58RNfbyKZ3q7MF%2F5mvNSC%2FXcSI%2F9mkEK8Blx2zD5dULoP9UK0MaSi049JL%2BUmo7K%2FHXgEH9dQ%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*XML 또는 JSON*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("cond[country_nm::EQ]","UTF-8") + "=" + URLEncoder.encode("가나", "UTF-8")); /*한글 국가명*/
	        urlBuilder.append("&" + URLEncoder.encode("cond[country_iso_alp2::EQ]","UTF-8") + "=" + URLEncoder.encode("GH", "UTF-8")); /*ISO 2자리코드*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
	        
	        uri = new URI(urlBuilder.toString());
	        
	        RestTemplate restTemplate = new RestTemplate();
	        HttpHeaders httpHeaders = new HttpHeaders();
	        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	        
	        response = restTemplate.getForEntity(uri, OpenApiDto2.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
	}

}
