package io.gourmand.dao;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import io.gourmand.domain.Review;
import io.gourmand.dto.RevDTO.RevRegister;

import io.gourmand.dto.RevDTO;
import io.gourmand.dto.RevDTO.RevThumbnail;

public interface ReviewRepository extends JpaRepository<Review, Long> {

//	void save(RevRegister entity);

	// 전체 목록 내림차순
    
    //별점높은순 반환  - review standard 평점이 없음 
//	@Query(value="select * from review order by review ", nativeQuery =true)
	 
	   //시간순 반환 - 첫째줄 혹은 두번째줄 따로 확인
//	     @Query(value = "select * from review order by review.writeDate = :writeDate asc", nativeQuery = true)
//	 	List<RevThumbnail> findAllReviewThumbnailByUserIdOrderByWriteDateDesc(@Param("userId") String userId);
	 
	   // 역시간순 반환   - 첫째줄 혹은 두번째줄 따로 확인
//	     @Query(value = "select * from review order by review.writeDate = :writeDate desc", nativeQuery = true)
//		List<RevThumbnail> findAllReviewThumbnailByUserIdOrderByWriteDateAsc(@Param("userId") String userId);

    //  페이징처리 - 윤혜 작업중  
//  Page<Post> findAll(Pageable pageable);
	
}
