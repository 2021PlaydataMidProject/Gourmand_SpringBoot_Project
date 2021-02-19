package io.gourmand.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.gourmand.domain.Review;
import io.gourmand.domain.User;
import io.gourmand.domain.UserResList;

public interface UserRepository extends JpaRepository<User, Long> {
	User findUserByUserId(String userId);
	
//  List<User> findUsersOfRes(Long id);

  //내가 작성한 리뷰개수  카운트
  @Query(value = "select user_id, count(review_num) from user,review where user.user_id = review.user_id", nativeQuery = true)
  Integer findcountbyReviewNum(@Param("reviewNum")Review reviewNum);    
  
  //내가 만든 리스트 카운트 
  @Query(value = "select user_id, count(list_num) from user,user_res_list where user.user_num = review.user_num", nativeQuery = true)
  Integer findcountByUserList(@Param("listNum")UserResList listNum);
  
  //선호입맛 리스트
  @Query(value = "select food_type from user,review where user.user_num = review.user_num order by food_type desc", nativeQuery = true)
  List<User> findListByfoodType(@Param("foodType") Review foodType);

  public User findByName(String name);

  List<User> findAll();
  
  Optional<User> findByUserId(String user_id);
  
  Optional<User> findByUserNum(Long user_num);
  
  
  
  
  

}

