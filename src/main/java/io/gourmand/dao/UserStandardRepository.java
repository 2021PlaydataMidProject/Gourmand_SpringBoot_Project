package io.gourmand.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import io.gourmand.domain.UserStandard;

public interface UserStandardRepository extends JpaRepository<UserStandard, Long> {

}

