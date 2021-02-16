package io.gourmand.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import io.gourmand.domain.User;
import io.gourmand.dto.UserDTO;
import io.gourmand.service.UserService;

@RestController
public class LoginController {

	@Autowired
	private UserService userService;

	@PostMapping("/auth/login")
	public User signin(@RequestBody @Validated UserDTO.SigninRequest request) {
		User user;
		try {
			user = userService.getMatchedUser(request);
			return user;
		} catch (Exception e) {
			return null;
		}
	}

	@PostMapping("/auth/check")
	public void check(@RequestBody User user) {
		System.out.println(user);
	}
	
	@GetMapping("auth/signout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:index.html";
	}
}
