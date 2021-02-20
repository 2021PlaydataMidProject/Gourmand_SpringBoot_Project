package io.gourmand.error.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.gourmand.exception.PasswordWrongException;
import io.gourmand.exception.UserIdNotExistedException;

@ControllerAdvice
public class SessionErrorAdvice {
	
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserIdNotExistedException.class)
	public String handleUserIdNotExisted() {
		return "{}";
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(PasswordWrongException.class)
	public String handlePasswordWrong() {
		return "{}";
	}
}
