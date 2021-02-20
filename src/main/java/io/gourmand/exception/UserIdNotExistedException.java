package io.gourmand.exception;

public class UserIdNotExistedException extends RuntimeException{

	public UserIdNotExistedException(String user_id) {
		super("User Id is not registered: " + user_id);
	}
}
