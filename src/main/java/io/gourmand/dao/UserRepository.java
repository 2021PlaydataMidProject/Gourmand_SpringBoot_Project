package io.gourmand.dao;

import org.springframework.data.repository.CrudRepository;

import io.gourmand.domain.User;

public interface UserRepository extends CrudRepository<User, String> {

}

