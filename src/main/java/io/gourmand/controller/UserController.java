package io.gourmand.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.gourmand.service.UserService;
import io.gourmand.dto.UserDTO.UserRegister;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	//private final UserService userService; //왜 안됨?
		
	// 회원 가입을 위한 User 정보 저장
	@PostMapping("/auth/signup")
	public void createUser(@RequestBody UserRegister user) {
		System.out.println("신규 회원 가입");
		userService.insertUser(user);
	}
	

}
