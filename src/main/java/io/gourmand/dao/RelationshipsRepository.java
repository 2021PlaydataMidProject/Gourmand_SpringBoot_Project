package io.gourmand.dao;

import org.springframework.data.repository.CrudRepository;


import io.gourmand.domain.Followers;

public interface RelationshipsRepository extends CrudRepository<Followers, Long> {

	

}
