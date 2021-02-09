package io.gourmand.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.gourmand.domain.Review;
import io.gourmand.domain.User;
import io.gourmand.dto.RevDTO.RevRegister;
import io.gourmand.service.RevService;

@RestController
public class RevController {

	@Autowired
	private RevService revService;
	
	// 모든 댓글 정보 조회
	@GetMapping("/rev/{id}/revsinfo")
	public String getReviewList(@ModelAttribute("user") User user, Model model, Review review) {
		
		if(user.getUserId() == null) {
			return "redirect:login";
		}
		
		List<Review> reviewList = revService.getReviewList(review);
		System.out.println(reviewList);
		return "reviewList";
	}
	
	// 하나의 댓글 정보 조회
	@GetMapping("/rev/{id}/revinfo")
	public String getReview(@ModelAttribute("user") User user, Review review, Model model) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}
		model.addAttribute("review", revService.getReview(review));
		return "getReview";
	}
	
	// 댓글 삭제
	@GetMapping("rev/deleteReview")
	public String deleteReview(@ModelAttribute("user") User user, Review review) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}
		revService.deleteReview(review);
		return "forward:getReview";
	}
	
	// 댓글 정보 저장
	@PostMapping("/rev/regi")
	public void createRev(@RequestBody RevRegister rev) {
		System.out.println(rev);
		revService.insertRev(rev);
	}
		

	

	
}
