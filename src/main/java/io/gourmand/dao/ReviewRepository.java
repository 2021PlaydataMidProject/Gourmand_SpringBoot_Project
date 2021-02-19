package io.gourmand.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import io.gourmand.domain.Review;
import io.gourmand.dto.RevDTO.RevRegister;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	void save(RevRegister entity);

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
	
	
	// 해당 가게의 리뷰 모두 가져오기
	@Query(value = "select review.* from review, res where res.res_num = :resNum and res.res_num = review.res_num", nativeQuery=true)
	List<Review> findAllByResNum(@Param("resNum") Long resNum);
	
	// 리뷰 시간순으로 12개까지 가져오기
	@Query(value = "select review.* from review, ( select count(*) as c from review ) cnt where review.review_num >= cnt.c-12 order by review.review_num desc", nativeQuery=true)
	List<Review> findAllOrderByDate();
	
}
