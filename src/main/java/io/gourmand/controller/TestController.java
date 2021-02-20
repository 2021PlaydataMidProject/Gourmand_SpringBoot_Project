package io.gourmand.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;


import org.springframework.security.core.Authentication;
import io.jsonwebtoken.Claims;

@RestController
public class TestController {

	@ResponseBody
	@RequestMapping("/test3")
	public static String test3() {
		JsonObject obj = new JsonObject();

		JsonObject data = new JsonObject();

		data.addProperty("user_num", "1");
		data.addProperty("user_id", "test");

		obj.add("data", data);

		return obj.toString();
	}

	public static void main(String[] args) {
		System.out.println(test3());
	}

	@GetMapping("/user")
	public String create(Authentication authentication) {
		Claims claims = (Claims) authentication.getPrincipal();

		String user_id = claims.get("user_id", String.class); // 토큰에 넣은 이름 적용 //user_id, user_num
		
		System.out.println("test " + user_id);

		String url = "/user/" + user_id;
		return url;
	}
	
}

//@RestController
//public class TestController {
//	@GetMapping("/api/hello")
//	public String hello() {
//		System.out.println("ddd");
//		return "String";
//	}
//}