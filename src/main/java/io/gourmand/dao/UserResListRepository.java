package io.gourmand.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import io.gourmand.domain.UserResList;

public interface UserResListRepository extends JpaRepository<UserResList, Long> {

}

