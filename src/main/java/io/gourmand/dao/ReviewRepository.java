package io.gourmand.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.gourmand.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	// 해당 가게의 리뷰 모두 가져오기
	@Query(value = "select review.* from review, res where res.res_num = :resNum and res.res_num = review.res_num", nativeQuery=true)
	List<Review> findAllByResNum(@Param("resNum") Long resNum);
	
	// 리뷰 시간순으로 12개까지 가져오기
	@Query(value = "select * from review, ( select count(*) as c from review ) cnt where review.review_num >= cnt.c-12 order by review.review_num desc", nativeQuery=true)
	List<Review> findAllOrderByDate();
	
}

