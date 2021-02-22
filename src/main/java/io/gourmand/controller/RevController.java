package io.gourmand.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
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

import io.gourmand.domain.Review;
import io.gourmand.domain.ReviewImg;
import io.gourmand.domain.ReviewStandard;
import io.gourmand.domain.User;
import io.gourmand.dto.RevDTO.RevInfo;
import io.gourmand.dto.RevDTO.RevRegister;
import io.gourmand.dto.RevDTO.ReviewThumbnail;
import io.gourmand.dto.ReviewStandardDTO.ReviewStandardRegister;
import io.gourmand.service.ResService;
import io.gourmand.service.RevService;
import io.gourmand.util.CookieUtil;
import io.gourmand.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RevController {

	@Autowired
	private RevService revService;
	@Autowired
	private ResService resService;

	@Value("${jwt.secret}")
	private String secret;

	// 모든 댓글 정보 조회
	@GetMapping("/rev/{id}/revsinfo")
	public String getReviewList(@ModelAttribute("user") User user, Model model, Review review) {
		
		if (user.getUserId() == null) {
			return "redirect:login";
		}

		List<Review> reviewList = revService.getReviewList(review);
		System.out.println(reviewList);
		return "reviewList";
	}

	// 하나의 댓글 정보 조회
	@GetMapping("/rev/{revNum}/revinfo")
	public RevInfo getReview(@PathVariable("revNum") Long revNum) {
		return RevInfo.of(revService.getReview(revNum));
	}

	// 댓글 정보 저장
	@PostMapping("/rev/regi")
	public void createRev(@RequestParam("reviewRegi") String rev, @RequestParam("resNum") String resNum,
			@RequestParam("reviewStandard") String rstandard, @RequestParam("revImg") List<MultipartFile> revImg,
			HttpServletRequest hsp) {
		
		Claims claim = new JwtUtil(secret).getClaims(CookieUtil.getCookie(hsp, "accessToken").getValue());
		Long userNum = claim.get("user_num", Long.class);
		log.info("댓글 저장 : 가게,"+ resNum + ",회원," + userNum);
		ObjectMapper mapper = new ObjectMapper();
		try {
			ReviewStandard rs = revService.insertRevSt(mapper.readValue(rstandard, ReviewStandardRegister.class));
			Review review = revService.insertRev(mapper.readValue(rev, RevRegister.class), Long.valueOf(userNum),
					Long.valueOf(resNum), rs);
			revImg.forEach(rmg -> {
				ReviewImg rimg = revService.insertRevImg(rmg, review);
				try {
					revService.saveImg(rmg, rimg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			resService.updateResAvgStar(Long.valueOf(resNum));
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
	}

	// 댓글 수정
	@PutMapping("/rev/{revNum}/update")
	public void updateRev(@PathVariable("revNum") Long revNum, @RequestParam("reviewRegi") String rev,
			@RequestParam("resNum") String resNum, @RequestParam("reviewStandard") String rstandard,
			@RequestParam("revImg") List<MultipartFile> revImg, HttpServletRequest hsp) {

		Claims claim = new JwtUtil(secret).getClaims(CookieUtil.getCookie(hsp, "accessToken").getValue());
		Long userNum = claim.get("user_num", Long.class);
		System.out.println(userNum);
		System.out.println(rev);
		System.out.println(rstandard);
		log.info("댓글 수정 : 가게,"+ resNum + ",회원," + userNum);
		ObjectMapper mapper = new ObjectMapper();
		try {
			Review review = revService.updateRev(mapper.readValue(rev, RevRegister.class),
					mapper.readValue(rstandard, ReviewStandardRegister.class), revNum, userNum);
			revImg.forEach(rmg -> {
				ReviewImg rimg = revService.insertRevImg(rmg, review);
				try {
					revService.saveImg(rmg, rimg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			resService.updateResAvgStar(Long.valueOf(resNum));
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
	}

	// 댓글 삭제
	@PostMapping("/rev/{res}/deleteReview/{num}")
	public void deleteReview(@PathVariable("num") Long revNum, @PathVariable("res") Long resNum,
			HttpServletRequest hsp) {
		Claims claim = new JwtUtil(secret).getClaims(CookieUtil.getCookie(hsp, "accessToken").getValue());
		Long userNum = claim.get("user_num", Long.class);
		log.info("댓글 삭제 : 가게,"+ resNum + ",회원," + userNum);
		revService.deleteReview(revNum, userNum);
		resService.updateResAvgStar(resNum);
	}

	// 댓글 이미지 삭제
	@PostMapping("/rev/delete/img")
	public void deleteRevImg(@RequestBody List<Long> id) {
		id.forEach(i -> {
			revService.deleteRevImg(i);
		});
	}

	// 가게에 대한 댓글 Thumbnail
	@GetMapping("/rev/res/{id}")
	public List<ReviewThumbnail> returnAllRevOfRes(@PathVariable Long id) {
		return revService.getRevThumbnailByResNum(id);
	}

	// 가게에 대한 standard 정보
	@GetMapping("/rev/res/{id}/standard")
	public String returnResStandard(@PathVariable Long id) {
		return revService.getResAvgRatingStandard(id);
	}

	// 시간순으로 12개의 revThumbnail
	@GetMapping("/rev/main/nologin")
	public List<ReviewThumbnail> returnAllOrderByTime() {
		return revService.getRevOrderByTime();
	}

	@GetMapping("/rev/user/cnt")
	public List<Long> returnAllRevNumOfUser(HttpServletRequest hsp) {
		Claims claim = new JwtUtil(secret).getClaims(CookieUtil.getCookie(hsp, "accessToken").getValue());
		Long userNum = claim.get("user_num", Long.class);
		System.out.println(userNum);
		return revService.getAllRevNumOfUser(userNum);
	}
}
