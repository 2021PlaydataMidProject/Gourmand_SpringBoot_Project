package io.gourmand.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.gourmand.domain.ReviewStandard;

public interface ReviewStandardRepository extends JpaRepository<ReviewStandard, Long> {
	
	// 가게의 평균 별점들을 받아온다.
	@Query(value="SELECT AVG(rating_standard.r_flavor) as r_flavor, AVG(rating_standard.r_clean) as r_clean, AVG(rating_standard.r_cost_value) as r_cost_value, AVG(rating_standard.r_mood) as r_mood, AVG(rating_standard.r_kindness) as r_kindness, AVG(rating_standard.r_access) as r_access "
				+ "FROM RATING_STANDARD, REVIEW "
				+ "WHERE :resNum = REVIEW.RES_NUM AND REVIEW.RS_ID = rating_standard.RS_ID", nativeQuery=true)
	String getResAvgStandard(@Param("resNum") Long resNum);
}

