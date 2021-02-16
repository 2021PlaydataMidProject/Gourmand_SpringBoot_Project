package io.gourmand.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import io.gourmand.domain.Review;
import io.gourmand.dto.RevDTO.RevRegister;

import io.gourmand.dto.RevDTO;
import io.gourmand.dto.RevDTO.ReviewThumbnail;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	void save(RevRegister entity);

//	// 전체 목록 내림차순
//    List<RevDTO> findAllByOrderByIdDesc(PageRequest pageRequest);
//    
// // 시간순 반환 
//  	@Query(value = "select * from review order by review.writeDate = :writeDate asc", nativeQuery = true)
//  	List<ReviewThumbnail> findAllOrderByWriteDate(@Param("writeDate") LocalDate writeDate);
//  	// 역시간순 반환 
//  	@Query(value = "select * from review order by review.writeDate = :writeDate desc", nativeQuery = true)
//  	List<ReviewThumbnail> findAllOrderByWriteDateReverse(@Param("writeDate") LocalDate writeDate);
}