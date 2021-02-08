package io.gourmand.dao;

import org.springframework.data.repository.CrudRepository;

import io.gourmand.domain.ReviewStandard;

public interface RatingStandardRepository extends CrudRepository<ReviewStandard, Long> {

}

