package io.gourmand.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import io.gourmand.domain.UserImg;

public interface UserImgRepository extends JpaRepository<UserImg, Long> {

}

