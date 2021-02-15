package io.gourmand.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.gourmand.service.UserService;
import io.gourmand.domain.Res;
import io.gourmand.domain.ResImg;
import io.gourmand.domain.User;
import io.gourmand.dto.ResDTO.ResRegister;
import io.gourmand.dto.UserDTO.UserRegister;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	//private final UserService userService; //final은 왜 안됨?
		
	@ModelAttribute("user") //왜 있나?
	public User setUser() {
		return new User();
	}

	// 회원 가입을 위한 User 정보 저장
	@PostMapping("/user/regi")
	public void createUser(@RequestParam("userImg" List<MultipartFile> userImg, @RequestParam("user" String userRegi) {
		System.out.println(userRegi);
		ObjectMapper mapper = new ObjectMapper();
		try {
			User user = userService.insertUser(mapper.readValue(resUser, UserRegister.class));
			userImg.forEach(img->{
				userImg ri = userService.insertUserImg(img, user);
				try {
					userService.saveImg(img, ri);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
//	//User 1인 조회
//	@GetMapping("user/{id}/userinfo")
//	public String getUser(@RequestBody UserRegister userId) {
//		if (userRegister.getUserId() == null) {
//			return "redirect:/auth/signup";
//		} return getUser(user);
//	}
//
//	
	// User id 관련 정보 삭제 - 테이블이외 또 삭제해야 할 user 정보가 있는지? (그 사람이 썼던 리뷰, 리스트 등)
	@GetMapping("/user/{id}/delete")
	public String deleteUser(@ModelAttribute("user") User user) {
		System.out.println("회원 정보 삭제");
		if (user.getUserId() == null) {
			return "redirect:/auth/signup";
		}
		userService.deleteUser(user);
		return "user";
	}

//	/*유저 정보를 변경한다. 
//	    *name, pw, job, pageStatus, standard
//	    * @param request 유저정보 변경
//	    *  세션에 아이디가 담아져 있는 상태여야함 
//	    */
//	   @PutMapping("/user/settings")
//	   public void editSettings(@RequestParam @Validated UserInfoEditRequest request) {
//	      userService.editUserInfo(userId, userInfo);
//	   }

}
