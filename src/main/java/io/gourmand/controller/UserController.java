package io.gourmand.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.gourmand.service.ResService;
import io.gourmand.service.UserService;
import io.gourmand.domain.User;
import io.gourmand.domain.UserImg;
import io.gourmand.domain.UserStandard;
import io.gourmand.dto.UserDTO.UserRegister;
import io.gourmand.dto.UserStandardDTO.UserStandardRegister;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private ResService resService;
	
		
	@ModelAttribute("user") //왜 있나?
	public User setUser() {
		return new User();
	}
	
	@ModelAttribute("userStandard") //왜 있나?
	public UserStandard setUserStandard() {
		return new UserStandard();
	}

	/* 회원 가입 */
	@PostMapping("/user/regi")
	public void createUser(@RequestParam("userImg") List<MultipartFile> userImg, @RequestParam("user") String userRegi, @RequestParam("userStandard") String userStandardregi) {
		System.out.println(userRegi);
		System.out.println(userStandardregi);
		ObjectMapper mapper = new ObjectMapper();
	try {
		UserStandard userStandard = userService.insertUserStandard(mapper.readValue(userStandardregi, UserStandardRegister.class));
		User user = userService.insertUser(mapper.readValue(userRegi, UserRegister.class), (userStandard));
		userImg.forEach(img->{
		UserImg uimg = userService.insertUserImg(img, user);
	try {
		userService.saveImg(img, uimg);
			} catch (IOException e) {
				e.printStackTrace();
			}
			});
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	//	 회원 기준 저장 
	@PostMapping("/user/regiNewStandard")
	public UserStandardRegister createUserStandard(@RequestBody UserStandardRegister userStandard) {
		System.out.println(userStandard);
		return userStandard;
	//System.out.println( "신규 회원 기준 저장" + userStandard.getId() );
	//userService.insertUserStandard(userStandard);
	}
	
	
	// 회원의 list 이름들 반환 (list에 저장용)
	@GetMapping("/user/reslist/{user}")
	public List<String> returnUserResList(@PathVariable("user") Long userid){
		return resService.getResListName(userid);
	}
	
	
	
	// 회원 1인의 전체 정보 가져오기 - 작업중
//	@GetMapping("/user/{id}/info")
//	public UserInfo getUserInfo(@PathVariable Long userNum) {
//	return userService.getUserInfo(userNum);
//	}
//	
//	/* 회원 정보 수정 */
//	@PutMapping("/user/{id}/update")
//	public void updateUser(@RequestParam("userImg") List<MultipartFile> userImg, @RequestParam("user") String userRegi, @RequestParam("userStandard") String userStandardregi) {
//		ObjectMapper mapper = new ObjectMapper();
//	try {
//		UserStandard userStandard = userService.insertUserStandard(mapper.readValue(userStandardregi, UserStandardRegister.class));
//		User user = userService.insertUser(mapper.readValue(userRegi, UserRegister.class), (userStandard));
//		userImg.forEach(img->{
//		UserImg uimg = userService.insertUserImg(img, user);
//	try {
//		userService.saveImg(img, uimg);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			});
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//	}
	
	
//	@DeleteMapping("/user/{id}") //(User 테이블 외에 다른 테이블 삭제할 거 고민해야 )
//	public String deleteUser(@RequestBody User user) {
//	userService.deleteUser(user);
//	return "탈퇴 완료";
//	}

//	/*유저 정보를 변경한다. 
//	    *name, pw, job, pageStatus, standard
//	    * @param request 유저정보 변경
//	    *  세션에 아이디가 담아져 있는 상태여야함 
//	    */
//	   @PutMapping("/user/settings")
//	   public void editSettings(@RequestParam @Validated UserInfoEditRequest request) {
//	      userService.editUserInfo(userId, userInfo);
//	   }

	//시간순 정렬 
//	   @GetMapping("/user/{id}/res/myratings/{reviewNum}")
//	   public List<ReviewDTO> getReviewByPageRequest(@PathVariable Long reviewNum,
//	          @RequestParam(value = "reverseAll", required = false) boolean reverseAll){
//	        PageRequest pageRequest = pageRequest.of(reviewNum, 30);
//	        
//	        if(pageRequest !=null) {
//	           return userService.findByPageRequest(pageRequest);
//	          
//	        }
//	        else if(reverseAll) {
//	           return userService.findByPageRequestReverse(pageRequest);
//	        }
//	   }
}
