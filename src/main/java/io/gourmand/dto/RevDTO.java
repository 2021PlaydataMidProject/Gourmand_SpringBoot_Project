package io.gourmand.dto;

import java.time.LocalDate;
import java.util.List;

import io.gourmand.domain.Res;
import io.gourmand.domain.Review;
import io.gourmand.domain.ReviewImg;
import io.gourmand.domain.ReviewStandard;
import io.gourmand.domain.User;
import io.gourmand.dto.UserDTO.UserThumbnail;
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
		private UserThumbnail user;
		private String foodType;
		private String review;
		private LocalDate writeDate;
		private ReviewStandard reviewStandard;
		private List<ReviewImg> reviewImg;

		public static RevInfo of(Review rev) {
			return RevInfo.builder().reviewNum(rev.getReviewNum())
					.foodType(rev.getFoodType()).review(rev.getReview()).writeDate(rev.getWriteDate())
					.reviewStandard(rev.getReviewStandard()).reviewImg(rev.getReviewImg())
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
		private String foodType;
		private String review;

		public static Review toEntity(RevRegister rev, User user, Res res, ReviewStandard rs) {
			return Review.builder()
					.user(user)
					.res(res)
					.foodType(rev.getFoodType())
					.review(rev.getReview())
					.writeDate(LocalDate.now())
					.reviewStandard(rs)
					.build();
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
		private UserThumbnail user;
		private Long resNum;
		private String resName;
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
			return ReviewThumbnail.builder().reviewNum(rev.getReviewNum()).user(UserThumbnail.of(rev.getUser()))
					.resNum(rev.getRes().getResNum()).resName(rev.getRes().getResName()).foodType(rev.getFoodType()).review(rev.getReview())
					.writeDate(rev.getWriteDate()).reviewImg(revImg).reviewStandard(rev.getReviewStandard()).build();
		}
	}
}
