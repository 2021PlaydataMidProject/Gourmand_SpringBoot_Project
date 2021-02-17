package io.gourmand.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.gourmand.domain.UserResList;

public interface UserResListRepository extends JpaRepository<UserResList, Long> {
	// 특정 유저의 ListName들 반환
	@Query(value = "select distinct(url.list_name) from user_res_list url, user ur where ur.user__num  == :user", nativeQuery = true)
	List<String> findListNamebyUser(@Param("user") Long userNum);

	// 특정 유저의 특정 리스트 반환
	@Query(value = "select * from user_res_list url where url.user_num = :userNum and url.list_name = :name", nativeQuery = true)
	List<UserResList> findAllbyUserAndListName(@Param("userNum") Long userNum, @Param("name") String resListName);
}