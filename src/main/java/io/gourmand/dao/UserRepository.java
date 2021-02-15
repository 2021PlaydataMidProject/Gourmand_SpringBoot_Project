package io.gourmand.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.gourmand.domain.User;
import io.gourmand.dto.UserDTO.UserInfo;

public interface UserRepository extends JpaRepository<User, String> {
	User findUserByUserId(String userId);

//	List<User> findUsersOfRes(Long id);

}

