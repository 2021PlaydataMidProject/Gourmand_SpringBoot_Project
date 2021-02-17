package io.gourmand.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;

import io.gourmand.domain.Res;
import io.gourmand.domain.Review;
import io.gourmand.domain.ReviewImg;
import io.gourmand.domain.ReviewStandard;
import io.gourmand.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class RevDTO {

	// 리뷰 조회
	@NoArgsConstructor
	@AllArgsConstructor
	@Setter
	@Getter
	@Builder
	public static class RevInfo {
		private Long reviewNum;
		private User user;
		private Res res;
		private String foodType;
		private String review;
		private LocalDate writeDate;
		private String imgLink;
		private ReviewStandard reviewStandard;
		private List<ReviewImg> reviewImg;

		public static RevInfo of(Review rev) {
			return RevInfo.builder().reviewNum(rev.getReviewNum()).user(rev.getUser()).res(rev.getRes())
					.foodType(rev.getFoodType()).review(rev.getReview()).writeDate(rev.getWriteDate())
					.imgLink(rev.getImgLink()).reviewStandard(rev.getReviewStandard()).reviewImg(rev.getReviewImg())
					.build();
		}
	}

	// 리뷰 등록
	@NoArgsConstructor
	@AllArgsConstructor
	@Setter
	@Getter
	@Builder
	public static class RevRegister {
		private User user;
		private Res res;
		private String foodType;
		private String review;
		private LocalDate writeDate;
		private String imgLink;
		private ReviewStandard reviewStandard;
		private List<ReviewImg> reviewImg;

		public static Review toEntity(RevRegister rev) {
			return Review.builder().user(rev.getUser()).res(rev.getRes()).foodType(rev.getFoodType())
					.review(rev.getReview()).writeDate(rev.getWriteDate()).imgLink(rev.getImgLink())
					.reviewStandard(rev.getReviewStandard()).reviewImg(rev.getReviewImg()).build();
		}
	}

	// 대표 이미지와 간단 설명
	@NoArgsConstructor
	@AllArgsConstructor
	@Setter
	@Getter
	@Builder
	public static class ReviewThumbnail {
		private Long reviewNum;
		private Long userNum;
		private Long resNum;
		private String foodType;
		private String review;
		private LocalDate writeDate;
		private ReviewImg reviewImg;
		private ReviewStandard reviewStandard;

		public static ReviewThumbnail of(Review rev) {
			ReviewImg revImg = null;
			if (rev.getReviewImg().size() > 0) {
				revImg = rev.getReviewImg().get(0);
			}
			return ReviewThumbnail.builder().reviewNum(rev.getReviewNum()).userNum(rev.getUser().getUserNum())
					.resNum(rev.getRes().getResNum()).foodType(rev.getFoodType()).review(rev.getReview())
					.writeDate(rev.getWriteDate()).reviewImg(revImg).reviewStandard(rev.getReviewStandard()).build();
		}
	}
	
	// standard들의 평균 별점
	@NoArgsConstructor
	@AllArgsConstructor
	@Setter
	@Getter
	public static class AvgRatingStandard {
		private BigDecimal rFlavor;		
		private BigDecimal rClean;
		private BigDecimal rCostValue;
		private BigDecimal rMood;
		private BigDecimal rKindness;
		private BigDecimal rAccess;
	}
}
