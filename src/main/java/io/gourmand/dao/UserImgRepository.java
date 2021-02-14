package io.gourmand.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.gourmand.domain.UserImg;

public interface UserImgRepository extends CrudRepository<UserImg, Long> {
	// user_num 으로 img 반환
	@Query(value = "select * from user_img where user_img.user_num = :userNum", nativeQuery=true)
	public List<UserImg> findAllByUserNum(@Param("userNum") Long userNum);
}

