package io.gourmand.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.gourmand.service.UserService;
import io.gourmand.domain.Res;
import io.gourmand.domain.ResImg;
import io.gourmand.domain.User;
import io.gourmand.dto.ResDTO.ResRegister;
import io.gourmand.dto.UserDTO.UserInfo;
import io.gourmand.dto.UserDTO.UserRegister;
import io.gourmand.dto.UserStandardDTO.UserStandardRegister;
import io.gourmand.dto.UserStandardDTO.UserStandardRegistertest;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	//private final UserService userService; //final은 왜 안됨?
		
	@ModelAttribute("user") //왜 있나?
	public User setUser() {
		return new User();
	}

	/* 회원 가입을 위한 User 정보 저장(with image) - /user/regi와 /user/regiUserStandard는 @Transactional로 처리해야 할듯
		아니면 두개 한번에 합치는 법 고민*/
	//@Transactional
	//@PostMapping("/user/regi")
	//public void createUser(@RequestParam("userImg") List<MultipartFile> userImg, @RequestParam("user") String userRegi) {
	//System.out.println(userRegi);
	//ObjectMapper mapper = new ObjectMapper();
	//mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);  //과연 해결해줄것인가!!!!!!!!!!!
	//try {
	//User user = userService.insertUser(mapper.readValue(userRegi, UserRegister.class));
	//userImg.forEach(img->{
	//UserImg uimg = userService.insertUserImg(img, user);
	//try {
	//userService.saveImg(img, uimg);
	//} catch (IOException e) {
	//e.printStackTrace();
	//}
	//});
	//} catch (Exception e) {
	//e.printStackTrace();
	//}
	//}
	//
	
	@PostMapping("/user/regi")
	public void createUser(@RequestBody UserRegister user) {
	System.out.println( "신규 회원 기준 저장" + user.getUserId() );
	userService.insertUser(user);
	}
	
	//	 회원 기준 저장 
	@PostMapping("/user/regiNewStandard")
	public UserStandardRegister createUserStandard(@RequestBody UserStandardRegister userStandard) {
		System.out.println(userStandard);
		return userStandard;
	//System.out.println( "신규 회원 기준 저장" + userStandard.getId() );
	//userService.insertUserStandard(userStandard);
	}
	
	// 회원 1인의 전체 정보 가져오기
	@GetMapping("/user/{id}/info")
	public UserInfo getUserInfo(@PathVariable String userId) {
	System.out.println("회원 정보 조회" + userId);
	return userService.getUserInfo(userId);
	}
	
	
	@DeleteMapping("/user/{id}") //(User 테이블 외에 다른 테이블 삭제할 거 고민해야 )
	public String deleteUser(@RequestBody User user) {
	userService.deleteUser(user);
	return "탈퇴 완료";
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
