//package io.gourmand.controller;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.time.LocalDate;
//import java.util.List;
//
//import javax.persistence.OneToMany;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import io.gourmand.domain.Res;
//import io.gourmand.domain.Review;
//import io.gourmand.domain.ReviewImg;
//import io.gourmand.domain.ReviewStandard;
//import io.gourmand.domain.User;
//import io.gourmand.service.RevService;
//import io.jsonwebtoken.Claims;
//
//@CrossOrigin
//@RestController
//public class ReviewController {
//
//    @Autowired
//    private RevService reviewService;
//
//    @PostMapping("/restaurants/{resNum}/reviews")
//    public ResponseEntity<?> create(
//            Authentication authentication,
//            @PathVariable("resNum") Long resNum,
//            @Valid @RequestBody Review resource,
//            @RequestBody User usersource,
//            @RequestBody Res ressource,
//            @RequestBody ReviewStandard revssource,
//            @RequestBody List<ReviewImg> revimagesource
//    ) throws URISyntaxException {
//        Claims claims = (Claims) authentication.getPrincipal();
//
//        String username = claims.get("name", String.class); // USER NAME을 JWT토큰으로 가져오려고 하는데 -> USER NAME 칼럼명이 없음 -> 리뷰 작성자 이름이 없음
//        //String username = usersource.getName();
//        String resname = ressource.getResName();
//        String foodType = resource.getFoodType();
//        String review = resource.getReview();
//        LocalDate writeDate = LocalDate.now();
//        String imgLink = resource.getImgLink();
//        Class<? extends ReviewStandard> reviewStandard = revssource.getClass();
//        Class<? extends List> reviewImg = revimagesource.getClass();
//        
//        Review review = reviewService.addReview(
//        		resNum, username, resname, foodType, review, writeDate, imgLink, reviewStandard, reviewImg );
//
//        String url = "/restaurant/" + resNum +
//                "/reviews/" + review.getReviewNum();
//        return ResponseEntity.created(new URI(url)).body("{}");
//    }
//
//}
//
//private Long reviewNum;
//
//private User user;
//
//private Res res;
//
//private String foodType;
//
//private String review;
//
//private LocalDate writeDate;
//
//private String imgLink;
//
//private ReviewStandard reviewStandard;
//
//@OneToMany(mappedBy = "review")
//private List<ReviewImg> reviewImg;
//

//@RestController
//public class RevController {
//
//	@Autowired
//	private RevService revService;
//	
//	// 모든 댓글 정보 조회
//	@GetMapping("/rev/{id}/revsinfo")
//	public String getReviewList(@ModelAttribute("user") User user, Model model, Review review) {
//		
//		if(user.getUserId() == null) {
//			return "redirect:login";
//		}
//		
//		List<Review> reviewList = revService.getReviewList(review);
//		System.out.println(reviewList);
//		return "reviewList";
//	}
//	
//	// 하나의 댓글 정보 조회
//	@GetMapping("/rev/{id}/revinfo")
//	public String getReview(@ModelAttribute("user") User user, Review review, Model model) {
//		if (user.getUserId() == null) {
//			return "redirect:login";
//		}
//		model.addAttribute("review", revService.getReview(review));
//		return "getReview";
//	}
//	
//	// 댓글 삭제
//	@GetMapping("rev/deleteReview")
//	public String deleteReview(@ModelAttribute("user") User user, Review review) {
//		if (user.getUserId() == null) {
//			return "redirect:login";
//		}
//		revService.deleteReview(review);
//		return "forward:getReview";
//	}
//	
//	// 댓글 정보 저장
//	@PostMapping("/rev/regi")
//	public void createRev(@RequestBody RevRegister rev) {
//		System.out.println(rev);
//		revService.insertRev(rev);
//	}
//	
//	//댓글 수정
//	@PostMapping("/rev/{id}/update")
//	public String updateRev(@ModelAttribute("user") User user, Review review) {
//		if (user.getUserId() == null) {
//			return "redirect:login";
//		}
//		revService.updateReview(review);
//		return "forward:getReview";
//	}
//
//}
