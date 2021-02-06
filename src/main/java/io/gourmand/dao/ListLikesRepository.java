package io.gourmand.dao;

import org.springframework.data.repository.CrudRepository;

import io.gourmand.domain.ListLikes;

public interface ListLikesRepository extends CrudRepository<ListLikes, Long> {

}
