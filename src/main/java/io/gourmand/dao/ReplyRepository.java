package io.gourmand.dao;

import org.springframework.data.repository.CrudRepository;
import io.gourmand.domain.Reply;

public interface ReplyRepository extends CrudRepository<io.gourmand.domain.Reply, Long> {

}

