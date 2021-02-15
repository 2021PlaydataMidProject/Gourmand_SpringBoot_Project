package io.gourmand.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import io.gourmand.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
	User findUserByUserId(String userId);
}

