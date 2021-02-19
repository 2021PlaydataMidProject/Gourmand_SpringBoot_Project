package io.gourmand.exception;

public class UserExistedException extends RuntimeException {
	
	public UserExistedException(String user_id) {
		super("USER ID is already registered: "+ user_id);
	}
}
