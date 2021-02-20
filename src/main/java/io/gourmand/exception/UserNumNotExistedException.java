package io.gourmand.exception;

public class UserNumNotExistedException extends RuntimeException {
	
	public UserNumNotExistedException(Long user_num) {
		super("User Number is not registered: " + user_num);
	}
}
