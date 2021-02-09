package io.gourmand.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import io.gourmand.domain.Review;
import io.gourmand.domain.User;
import io.gourmand.dto.RevDTO.RevRegister;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	void save(RevRegister entity);


}

