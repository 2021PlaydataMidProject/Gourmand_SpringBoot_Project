package io.gourmand.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.gourmand.dao.ReviewRepository;
import io.gourmand.domain.Review;
import io.gourmand.dto.RevDTO.RevInfo;
import io.gourmand.dto.RevDTO.RevRegister;

@Service
public class RevService {
	
	@Autowired
	private ReviewRepository revDAO;
	
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
	
	

}
