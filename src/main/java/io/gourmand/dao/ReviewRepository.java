package io.gourmand.dao;

import org.springframework.data.repository.CrudRepository;

import io.gourmand.domain.Review;
import io.gourmand.dto.RevDTO.RevRegister;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	void save(RevRegister entity);

}

