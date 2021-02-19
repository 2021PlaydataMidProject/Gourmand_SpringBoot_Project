package io.gourmand.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import io.gourmand.dto.RevDTO.RevRegister;
import io.gourmand.dto.RevDTO.ReviewThumbnail;
import io.gourmand.dto.ReviewStandardDTO;
import io.gourmand.dto.ReviewStandardDTO.ReviewStandardRegister;
import io.gourmand.service.RevService;

@RestController
public class RevController {

	@Autowired
	private RevService revService;

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
	public void createRev(@RequestParam("reviewRegi") String rev, @RequestParam("userNum") String userNum,
			@RequestParam("resNum") String resNum, @RequestParam("reviewStandard") String rstandard,
			@RequestParam("revImg") List<MultipartFile> revImg) {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(rev);
		System.out.println(userNum);
		System.out.println(resNum);
		System.out.println(rstandard);
		
		try {
			ReviewStandard rs = revService.insertRevSt(mapper.readValue(rstandard, ReviewStandardRegister.class));
			Review review = revService.insertRev(mapper.readValue(rev, RevRegister.class), Long.valueOf(userNum), Long.valueOf(resNum), rs);
			revImg.forEach(rmg -> {
				ReviewImg rimg = revService.insertRevImg(rmg, review);
				try {
					revService.saveImg(rmg, rimg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
		
	}

	// 댓글 수정
	@PostMapping("/rev/{id}/update")
	public String updateRev(@ModelAttribute("user") User user, Review review) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}
		revService.updateReview(review);
		return "forward:getReview";
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
}
