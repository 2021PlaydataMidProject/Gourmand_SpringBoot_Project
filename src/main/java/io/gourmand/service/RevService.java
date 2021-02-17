package io.gourmand.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.gourmand.dao.ReviewRepository;
import io.gourmand.dao.ReviewStandardRepository;
import io.gourmand.domain.Review;
import io.gourmand.dto.RevDTO.AvgRatingStandard;
import io.gourmand.dto.RevDTO.RevInfo;
import io.gourmand.dto.RevDTO.RevRegister;
import io.gourmand.dto.RevDTO.ReviewThumbnail;

@Service
public class RevService {
	
	@Autowired
	private ReviewRepository revDAO;
	@Autowired
	private ReviewStandardRepository rsDAO;
	
	// 모든 댓글 조회 
	public List<Review> getReviewList(Review review) {
		return (List<Review>) revDAO.findAll();
	}
	
	// 하나의 댓글 조회 
	public RevInfo getReview(Review review) {
		return RevInfo.of(revDAO.findById(review.getReviewNum()).get());
	}
	
	// 댓글 삭제
	public void deleteReview(Review review) {
		revDAO.deleteById(review.getReviewNum());
	}
	
	// 댓글 등록
	public void insertRev(RevRegister rev) {
		revDAO.save(RevRegister.toEntity(rev));
	}
	
	// 댓글 수정
	public void updateReview(Review review) {
		Review findReview = revDAO.findById(review.getReviewNum()).get();
		
		findReview.setReview(review.getReview());
		findReview.setReviewImg(review.getReviewImg());
		revDAO.save(findReview);
	}
	
	// 가게에 대한 댓글Thumbnail 반환
	public List<ReviewThumbnail> getRevThumbnailByResNum(Long resNum){
		List<ReviewThumbnail> revt = new ArrayList<>();
		revDAO.findAllByResNum(resNum).forEach(r -> revt.add(ReviewThumbnail.of(r)));
		return revt;
	}
	
	// 가게의 reviewstandard
	public String getResAvgRatingStandard(Long id) {
		return rsDAO.getResAvgStandard(id);
	}
}
