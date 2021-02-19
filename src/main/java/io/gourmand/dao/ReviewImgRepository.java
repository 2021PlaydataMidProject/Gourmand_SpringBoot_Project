package io.gourmand.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import io.gourmand.domain.ReviewImg;

public interface ReviewImgRepository extends JpaRepository<ReviewImg, Long> {
	
}
