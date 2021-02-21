package io.gourmand.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.gourmand.service.ResService;
import io.gourmand.service.UserService;
import io.gourmand.domain.Review;
import io.gourmand.domain.ReviewImg;
import io.gourmand.domain.ReviewStandard;
import io.gourmand.domain.User;
import io.gourmand.domain.UserImg;
import io.gourmand.domain.UserStandard;
import io.gourmand.dto.RevDTO.RevRegister;
import io.gourmand.dto.RevDTO.ReviewThumbnail;
import io.gourmand.dto.ReviewStandardDTO.ReviewStandardRegister;
import io.gourmand.dto.UserDTO.UserInfo;
import io.gourmand.dto.UserDTO.UserRegister;
import io.gourmand.dto.UserStandardDTO.UserStandardRegister;

@RestController
public class UserController {

   @Autowired
   private UserService userService;
   @Autowired
   private ResService resService;
   
   /* 회원 가입 */
   @PostMapping("/user/regi")
   public void createUser(@RequestParam("userImg") List<MultipartFile> userImg, @RequestParam("user") String userRegi, @RequestParam("userStandard") String userStandardregi) {
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
   
   // 유저 정보 수정
   @PutMapping("/user/{userNum}/update")
   public void updateUser(@PathVariable("userNum") Long userNum, @RequestParam("userStandard") String userStandard, @RequestParam("userImg") List<MultipartFile> userImg, @RequestParam("user") String userRegi) {
      ObjectMapper mapper = new ObjectMapper();
   try {
//      UserStandard userStandard = userService.updateUserStandard(mapper.readValue(userStandardregi, UserStandardRegister.class));
      User user = userService.updateUser(mapper.readValue(userRegi, UserRegister.class), mapper.readValue(userStandard, UserStandardRegister.class), userNum);
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
      
   // 회원 삭제
   @DeleteMapping("/user/{userNum}") //(User 테이블 외에 다른 테이블 삭제할 거 고민해야 )
   public void deleteUser(@PathVariable("userNum") Long userNum) {
      userService.deleteUser(userNum);
   }
   
	// 회원 이미지 삭제
	@DeleteMapping("/user/delete/img")
	public void deleteUserImg(@RequestBody List<Long> id) {
		id.forEach(i -> {
			userService.deleteUserImg(i);
		});
	}

   
   //    회원 기준 저장 
   @PostMapping("/user/regiNewStandard")
   public UserStandardRegister createUserStandard(@RequestBody UserStandardRegister userStandard) {
      System.out.println(userStandard);
      return userStandard;
   }
   
   
   // 회원의 list 이름들 반환 (list에 저장용)
   @GetMapping("/user/reslist/{user}")
   public List<String> returnUserResList(@PathVariable("user") Long userid){
      return resService.getResListName(userid);
   }
   
    //내가 작성한 리스트 카운트 불러오는거 //안됨 - 500
    @GetMapping("/user/{userNum}/count/list")
    public Long getUserCountByList(@PathVariable Long userNum) {
        return userService.getUserListCounts(userNum);
    }    
   
   // 회원 1인의 전체 정보 가져오기 
   @GetMapping("/user/{userNum}/info")
   public UserInfo getUserInfo(@PathVariable Long userNum) {
      return userService.getUserInfo(userNum);
   }
   
   //내가 작성한 리뷰개수 카운트 불러오는거  //안됨 - 500
    @GetMapping("/user/{userNum}/count/review")
    public Long getUserCountByReview(@PathVariable Long userNum) {
       return userService.getUserReviewCounts(userNum);
    }
      
    //선호 food_type 갯수로 내림 차순 //안됨 - 500
    @GetMapping("/user/{userNum}/userAnalysis/foodType")
    public List<String> getFoodTypeByReview(@PathVariable Long userNum){
        return userService.getFoodTypeByReview(userNum);
    }
      
     //유저 당 리뷰를 시간순으로 반환 
     @GetMapping("/res/user/{userNum}/review/writeDate")
     public List<ReviewThumbnail> getAllOrderByUserNumNDate(@PathVariable Long userNum){
        return userService.getAllOrderByUserNumNDate(userNum);
     }
    
     //유저 당 리뷰를 별점순으로 반환 
     @GetMapping("/res/user/{userNum}/review/Star")
     public List<ReviewThumbnail> getAllOrderByUserNumNStar(@PathVariable Long userNum){
        return userService.getAllOrderByUserNumNStar(userNum);
     }
}