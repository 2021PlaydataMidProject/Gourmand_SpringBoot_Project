package io.gourmand.dao;

import org.springframework.data.repository.CrudRepository;

import io.gourmand.domain.UserStandard;

public interface UserStandardRepository extends CrudRepository<UserStandard, Long> {

}

