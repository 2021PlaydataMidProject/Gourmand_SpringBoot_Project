package io.gourmand.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.gourmand.domain.Res;
import io.gourmand.domain.User;

public interface ResRepository extends JpaRepository<Res, Long> {

	Optional<Res> findByResName(String resname);
	
	// 카테고리(음식종류) 별 반환 (거리순)
	@Query(value = "select * from res where res.category = :category order by res.x_value + res.y_value - :xValue - :yValue asc", nativeQuery = true)
	List<Res> findAllbyCategory(@Param("category") String category, @Param("xValue") BigDecimal xValue, @Param("yValue") BigDecimal yValue);

	// 거리순 반환
	@Query(value = "select * from res where pow(:limit,2) >= pow(res.x_value - :xValue,2) + pow(res.y_value - :yValue,2) order by pow(res.x_value - :xValue,2) + pow(res.y_value - :yValue,2) asc", nativeQuery = true)
	List<Res> findAllOrderByAxis(@Param("xValue") BigDecimal xValue, @Param("yValue") BigDecimal yValue, @Param("limit") BigDecimal limit);
		
	// 평균 평점별 반환
	@Query(value = "select * from res where pow(:limit,2) >= pow(res.x_value - :xValue,2) + pow(res.y_value - :yValue,2) order by avg_star desc", nativeQuery = true)
	List<Res> findAllbyAvgStar(@Param("xValue") BigDecimal xValue, @Param("yValue") BigDecimal yValue, @Param("limit") BigDecimal limit);
	
	// 음식점 검색
	@Query(value = "select * from res where pow(:limit,2) >= pow(res.x_value - :xValue,2) + pow(res.y_value - :yValue,2) and res_name like %:search% order by pow(res.x_value - :xValue,2) + pow(res.y_value - :yValue,2) asc" , nativeQuery=true)
	List<Res> findAllbyResName(@Param("xValue") BigDecimal xValue, @Param("yValue") BigDecimal yValue, @Param("limit") BigDecimal limit, @Param("search") String search);
	
	// 음식점 평균 별점 갱신
	@Modifying
	@Transactional
	@Query(value = "update res set avg_star=(select (avg(rating_standard.r_access)+avg(rating_standard.r_clean)+avg(rating_standard.r_cost_value)+avg(rating_standard.r_flavor)+avg(rating_standard.r_kindness)+avg(rating_standard.r_mood))/6 "
	+ "from rating_standard, review "
	+ "where :resNum = review.res_num and review.rs_id = rating_standard.rs_id) where res_num = :resNum", nativeQuery=true)
	void updateResAvgStar(@Param("resNum") Long resNum);
	
}
