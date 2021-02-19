package io.gourmand.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.gourmand.domain.Res;

public interface ResRepository extends JpaRepository<Res, Long> {

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
}
