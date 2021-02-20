package io.gourmand.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.gourmand.domain.User;
import io.gourmand.dto.UserDTO;
import io.gourmand.dto.UserDTO.SigninResponse;
import io.gourmand.service.UserService;

@RestController
public class LoginController {

	@Autowired
	private UserService userService;

	@PostMapping("/auth/login")
	public SigninResponse signin(
			@RequestBody @Validated UserDTO.SigninRequest request, 
			HttpServletResponse res){
		try {
			return userService.getMatchedUser(request, res);
		} catch (Exception e) {
			return null;
		}
	}
	
	@PostMapping("/auth/logout")
	public void signout(HttpServletResponse res){
		userService.logout(res);
	}

	@PostMapping("/auth/check")
	public void check(@RequestBody User user) {
		System.out.println(user);
	}
	
	
	@RequestMapping(value="/some/path", method = RequestMethod.POST)
	public void someMethod(HttpServletResponse response) {
	    //원래 쿠키의 이름이 userInfo 이었다면, value를 null로 처리.
	    Cookie myCookie = new Cookie("accessToken", null);
	    myCookie.setMaxAge(0); // 쿠키의 expiration 타임을 0으로 하여 없앤다.
	    myCookie.setPath("/"); // 모든 경로에서 삭제 됬음을 알린다.
	    response.addCookie(myCookie);
	}
	
	
//	@GetMapping("/auth/signout")
//	public HttpServletResponse logout(HttpServletRequest request, HttpServletResponse response) {
//		
//		Cookie[] cookies = request.getCookies();
//		
//		if(cookies != null) {
//			for(int i =0; i < cookies.length; i++) {
//				cookies[i].setMaxAge(0);
//				response.addCookie(cookies[i]);
//			}
//		}
		
//		Cookie cookie = new Cookie("accessToken", null); // choiceCookieName(쿠키 이름)에 대한 값을 null로 지정
//		cookie.setPath("/");
//		cookie.setValue("");
//	    cookie.setMaxAge(0); // 유효시간을 0으로 설정
//	    System.out.println("test--11111");
//	    response.addCookie(cookie); // 응답 헤더에 추가해서 없어지도록 함
//	    System.out.println("test--222");
	    
//		return response;

}
