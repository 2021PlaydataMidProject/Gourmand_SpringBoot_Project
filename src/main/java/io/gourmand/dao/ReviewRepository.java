package io.gourmand.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import io.gourmand.domain.Review;
import io.gourmand.dto.RevDTO.RevRegister;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	void save(RevRegister entity);


	// 해당 가게의 리뷰 모두 가져오기
	@Query(value = "select review.* from review, res where res.res_num = :resNum and res.res_num = review.res_num", nativeQuery=true)
	List<Review> findAllByResNum(@Param("resNum") Long resNum);
	
	// 리뷰 시간순으로 12개까지 가져오기
	@Query(value = "select review.* from review, ( select count(*) as c from review ) cnt where review.review_num >= cnt.c-12 order by review.review_num desc", nativeQuery=true)
	List<Review> findAllOrderByDate();
	
	//특정 유저가 작성한 리뷰 최신순으로 가져오기
	@Query(value = "select review.* from review, user ur where ur.user_num  = :user and review.user_num = ur.user_num order by review.review_num desc", nativeQuery = true)
	List<Review> findAllOrderByUserNumNDate(@Param("user") Long userNum);
	   
	//특정 유저가 작성한 리뷰 별점높은순으로 가져오기 
	@Query(value = "select review.* from review, user ur where ur.user_num  = :user and review.user_num = ur.user_num order by review.rs_id desc", nativeQuery = true)
	List<Review> findAllOrderByUserNumNStar(@Param("user") Long userNum);

	
}
