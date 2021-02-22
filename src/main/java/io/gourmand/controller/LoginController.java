package io.gourmand.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.gourmand.dto.UserDTO;
import io.gourmand.dto.UserDTO.SigninResponse;
import io.gourmand.service.UserService;

@RestController
public class LoginController {

	@Autowired
	private UserService userService;

	@PostMapping("/auth/login")
	public SigninResponse signin(@RequestBody @Validated UserDTO.SigninRequest request, HttpServletResponse res){
		try {
			return userService.getMatchedUser(request, res);
		} catch (Exception e) {
			return null;
		}
	}
	
	@PostMapping("/auth/logout")
	public void logout(HttpServletResponse res) {
		userService.logout(res);
	}
}
