package io.gourmand.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.gourmand.domain.User;

public interface UserRepository extends CrudRepository<User, String> {
	User findUserByUserId(String userId);
	
	// 해당 가게를 리스트에 등록한 유저 반환
	@Query(value = "select user.* from user, res, user_res_list where res.res_num = :resNum and res.res_num = user_res_list.res_num and user_res_list.user_num = user.user_num", nativeQuery=true)
	List<User> findUsersOfRes(@Param("resNum") Long resNum);
}

