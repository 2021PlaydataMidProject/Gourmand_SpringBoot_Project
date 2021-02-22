package io.gourmand.exception;

import io.gourmand.exception.common.BusinessException;
import io.gourmand.exception.common.ErrorCode;

public class EntityNoyExistedException extends BusinessException {

	 public EntityNoyExistedException(ErrorCode error) {
		 super(error);
	 }
}
