package io.gourmand.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import io.gourmand.domain.Res;
import io.gourmand.domain.ResImg;
import io.gourmand.domain.Review;
import io.gourmand.domain.ReviewImg;
import io.gourmand.domain.ReviewStandard;
import io.gourmand.domain.User;
import io.gourmand.dto.ResDTO.ResInfo;
import io.gourmand.dto.ResDTO.ResRegister;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class RevDTO {

	
	
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
			return Review.builder()
					.user(rev.getUser())
					.res(rev.getRes())
					.foodType(rev.getFoodType())
					.review(rev.getReview())
					.writeDate(rev.getWriteDate())
					.imgLink(rev.getImgLink())
					.reviewStandard(rev.getReviewStandard())
					.reviewImg(rev.getReviewImg())
					.build();
		}
	}
	
	
	
}
