package io.gourmand.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import io.gourmand.domain.User;
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

	
//	@PostMapping("/auth/login")
//	public LoginResponse signin(@RequestBody @Validated UserDTO.SigninRequest request, HttpServletResponse res) {
//
//		try {
//			final User user = userService.getMatchedUser(request);
//			final String token = jwtUtil.generateToken(user);
//			Cookie accessToken = CookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
//			res.addCookie(accessToken);
//			return new LoginResponse("success", "로그인에 성공했습니다.", token);
//		} catch (Exception e) {
//			return new LoginResponse("error", "로그인에 실패했습니다.", e.getMessage());
//		}
//	}
	
/*
	public Response login(@RequestBody RequestLoginUser user, HttpServletRequest req, HttpServletResponse res) {
		try {
			final Member member = authService.loginUser(user.getUsername(), user.getPassword());
			final String token = jwtUtil.generateToken(member);
			final String refreshJwt = jwtUtil.generateRefreshToken(member);
			Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
			Cookie refreshToken = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_NAME, refreshJwt);
			redisUtil.setDataExpire(refreshJwt, member.getUsername(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);
			res.addCookie(accessToken);
			res.addCookie(refreshToken);
			return new Response("success", "로그인에 성공했습니다.", token);
		} catch (Exception e) {
			return new Response("error", "로그인에 실패했습니다.", e.getMessage());
		}
	}
*/
	
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
