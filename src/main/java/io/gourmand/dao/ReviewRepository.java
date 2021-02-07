package io.gourmand.dao;

import org.springframework.data.repository.CrudRepository;

import io.gourmand.domain.Review;

public interface ReviewRepository extends CrudRepository<Review, Long> {

}

