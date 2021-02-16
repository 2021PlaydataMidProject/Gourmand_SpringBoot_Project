package io.gourmand.dto;

import java.time.LocalDate;
import java.util.List;

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


public class ReviewDTO {
   
   
   
   
   //대표 이미지와 간단 설명
      @NoArgsConstructor
      @AllArgsConstructor
      @Setter
      @Getter
      @Builder
      public static class ReviewThumbnail {
         private Long reviewNum;
         private User user;
         private Res res;
         private String foodType;
         private String review;
         private LocalDate writeDate;
         private List<ReviewImg> reviewImg;
         private ReviewStandard reviewStandard;
         
         
         public static ReviewThumbnail of(ReviewThumbnail review) {
            ReviewImg revImg = null;
            if (review.getReviewImg().size()>0) {
               revImg = review.getReviewImg().get(0);
            }
            return ReviewThumbnail.builder()
            .reviewNum(review.getReviewNum())
            .user(review.getUser())
            .res (review.getRes())
            .foodType ( review.getFoodType())
            .review (review.getReview())
            .writeDate (review.getWriteDate())
            .reviewImg (review.getReviewImg())
            .reviewStandard (review.getReviewStandard())
            .build();
         }


   
         }
   
   

}