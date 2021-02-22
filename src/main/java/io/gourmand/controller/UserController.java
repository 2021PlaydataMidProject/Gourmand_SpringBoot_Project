package io.gourmand.controller;

import java.io.IOException;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import io.gourmand.dto.RevDTO.ReviewThumbnail;

import io.gourmand.util.CookieUtil;
import io.gourmand.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import io.gourmand.dto.ResDTO.ResThumbnail;

import io.gourmand.dto.UserDTO.UserInfo;
import io.gourmand.dto.UserDTO.UserRegister;
import io.gourmand.dto.UserDTO.UserSimple;
import io.gourmand.dto.UserStandardDTO.UserStandardRegister;

@Slf4j
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private ResService resService;

	@Value("${jwt.secret}")
	private String secret;

	/* 회원 가입 */
	@PostMapping("/user/regi") // "/auth/regi"
	public void createUser(@RequestParam("userImg") List<MultipartFile> userImg, @RequestParam("user") String userRegi,
			@RequestParam("userStandard") String userStandardregi) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			UserStandard userStandard = userService
					.insertUserStandard(mapper.readValue(userStandardregi, UserStandardRegister.class));
			User user = userService.insertUser(mapper.readValue(userRegi, UserRegister.class), (userStandard));
			log.info("회원가입 : " + user.getUserId());
			userImg.forEach(img -> {
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
	@PutMapping("/user/update")
	public void updateUser(HttpServletRequest hsp, @RequestParam("userStandard") String userStandard,
			@RequestParam("userImg") List<MultipartFile> userImg, @RequestParam("user") String userRegi) {
		{
			Claims claim = new JwtUtil(secret).getClaims(CookieUtil.getCookie(hsp, "accessToken").getValue());
			Long userNum = claim.get("user_num", Long.class);
			ObjectMapper mapper = new ObjectMapper();
			try {
				User user = userService.updateUser(mapper.readValue(userRegi, UserRegister.class),
						mapper.readValue(userStandard, UserStandardRegister.class), userNum);
				log.info("회원수정 : " + user.getUserId());
				userImg.forEach(img -> {
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
	}

	// 회원 기준 삭제
	@DeleteMapping("/user/userstandard")
	public void deleteUserStandardUser(HttpServletRequest hsp) {
		Claims claim = new JwtUtil(secret).getClaims(CookieUtil.getCookie(hsp, "accessToken").getValue());
		Long id = claim.get("user_num", Long.class);
		userService.deleteUserStandard(id);
	}

	// 회원 삭제
	@DeleteMapping("/user")
	public void deleteUser(HttpServletRequest hsp) {
		Claims claim = new JwtUtil(secret).getClaims(CookieUtil.getCookie(hsp, "accessToken").getValue());
		Long userNum = claim.get("user_num", Long.class);
		log.info("회원탈퇴 : " + userNum);
		userService.deleteUser(userNum);
	}

	// 회원 이미지 삭제
	@DeleteMapping("/user/img")
	public void deleteUserImg(@RequestBody List<Long> id) {
		id.forEach(i -> {
			userService.deleteUserImg(i);
		});
	}

	// 회원 기준 저장
	@PostMapping("/user/regiNewStandard")
	public UserStandardRegister createUserStandard(@RequestBody UserStandardRegister userStandard) {
		return userStandard;
	}

	// 회원의 list 이름들 반환 (list에 저장용)
	@GetMapping("/user/reslist/")
	public List<String> returnUserResList(HttpServletRequest hsp) {
		Claims claim = new JwtUtil(secret).getClaims(CookieUtil.getCookie(hsp, "accessToken").getValue());
		Long userNum = claim.get("user_num", Long.class);
		return resService.getResListName(userNum);
	}

	// 인기 많은 유저의 아이디와 닉네임
	@GetMapping("/user/popular")
	public List<UserSimple> getUserIdOfFamousUser() {
		return userService.getFamousUsers();
	}

	// 내 리스트 불러오기
	@GetMapping("/user/list")
	public Map<String, List<ResThumbnail>> getResOfList(HttpServletRequest hsp) {
		Claims claim = new JwtUtil(secret).getClaims(CookieUtil.getCookie(hsp, "accessToken").getValue());
		Long userNum = claim.get("user_num", Long.class);

		Map<String, List<ResThumbnail>> resList = new HashMap<>();
		resService.getResListName(userNum)
				.forEach(name -> resList.put(name, resService.getAllResOfList(userNum, name)));
		return resList;
	}

	// 내가 작성한 리스트 카운트 불러오는거
	@GetMapping("/user/count/list")
	public Long getUserCountByList(HttpServletRequest hsp) {
		Claims claim = new JwtUtil(secret).getClaims(CookieUtil.getCookie(hsp, "accessToken").getValue());
		Long userNum = claim.get("user_num", Long.class);
		return userService.getUserListCounts(userNum);
	}

	// 내 전체 정보 가져오기
	@GetMapping("/user/info")
	public UserInfo getUserInfo(HttpServletRequest hsp) {
		Claims claim = new JwtUtil(secret).getClaims(CookieUtil.getCookie(hsp, "accessToken").getValue());
		Long userNum = claim.get("user_num", Long.class);
		return userService.getUserInfo(userNum);
	}

	// 회원 리스트 불러오기
	@GetMapping("/user/list/{id}")
	public Map<String, List<ResThumbnail>> getResOfList(@PathVariable("id") Long id) {
		Map<String, List<ResThumbnail>> resList = new HashMap<>();
		resService.getResListName(id).forEach(name -> resList.put(name, resService.getAllResOfList(id, name)));
		return resList;
	}

	// 리스트 카운트 불러오는거
	@GetMapping("/user/count/list/{id}")
	public Long getUserCountByList(@PathVariable("id") Long id) {
		return userService.getUserListCounts(id);
	}

	// 전체 정보 가져오기
	@GetMapping("/user/info/{id}")
	public UserInfo getUserInfo(@PathVariable("id") Long id) {
		return userService.getUserInfo(id);
	}

	// 선호 food_type 갯수로 내림 차순 //안됨 - 500
	@GetMapping("/user/{userNum}/userAnalysis/foodType")
	public List<String> getFoodTypeByReview(@PathVariable Long userNum) {
		return userService.getFoodTypeByReview(userNum);
	}

	// 유저 당 리뷰를 시간순으로 반환
	@GetMapping("/res/user/review/writeDate")
	public List<ReviewThumbnail> getAllOrderByUserNumNDate(HttpServletRequest hsp) {
		Claims claim = new JwtUtil(secret).getClaims(CookieUtil.getCookie(hsp, "accessToken").getValue());
		Long userNum = claim.get("user_num", Long.class);
		return userService.getAllOrderByUserNumNDate(userNum);
	}

	// 유저 당 리뷰를 별점순으로 반환
	@GetMapping("/res/user/review/Star")
	public List<ReviewThumbnail> getAllOrderByUserNumNStar(HttpServletRequest hsp) {
		Claims claim = new JwtUtil(secret).getClaims(CookieUtil.getCookie(hsp, "accessToken").getValue());
		Long userNum = claim.get("user_num", Long.class);
		return userService.getAllOrderByUserNumNStar(userNum);
	}

	// 유저 당 리뷰를 시간순으로 반환
	@GetMapping("/res/user/review/writeDate/{id}")
	public List<ReviewThumbnail> getAllOrderByUserNumNDate(@PathVariable("id") Long id) {
		return userService.getAllOrderByUserNumNDate(id);
	}

	// 유저 당 리뷰를 별점순으로 반환
	@GetMapping("/res/user/review/Star/{id}")
	public List<ReviewThumbnail> getAllOrderByUserNumNStar(@PathVariable("id") Long id) {
		return userService.getAllOrderByUserNumNStar(id);
	}
}
