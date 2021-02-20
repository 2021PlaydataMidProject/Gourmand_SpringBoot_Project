package io.gourmand.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//JWT 토큰 안에 있는 값 가져올때 import
import org.springframework.security.core.Authentication;
import io.jsonwebtoken.Claims;


@RestController
public class TestController {
	@GetMapping("/api/hello")
	public String hello() {
		System.out.println("ddd");
		return "String";
	}
	
	//JWT 토큰 테스트
	@GetMapping("/user")
	public String create(Authentication authentication) {
		Claims claims = (Claims) authentication.getPrincipal();

		String user_id = claims.get("user_id", String.class); // 토큰에 넣은 이름 적용 //user_id, user_num
		
		System.out.println("test " + user_id);

		String url = "/user/" + user_id;
		return url;
	}
	// Header에 아래 값 넣어주기
	//	KEY : Authorization
	//	VALUE : Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX251bSI6NCwidXNlcl9pZCI6InRlc3Q2IiwibmFtZSI6Imp3In0.9IZJIHCQ7jEjh31vZye5SZwzrnrWEkwLWHug2hDnoME
	//	주의사항 : 'Bearer ' -> 토큰 앞에 꼭 붙여주기

	
	
	
	
}
