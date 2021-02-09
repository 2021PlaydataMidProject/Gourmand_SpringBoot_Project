package io.gourmand.dao;

import org.springframework.data.repository.CrudRepository;
import io.gourmand.domain.Thread;

public interface ThreadRepository extends CrudRepository<io.gourmand.domain.Thread, Long> {

}

