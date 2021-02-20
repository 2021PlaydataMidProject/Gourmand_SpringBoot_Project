package io.gourmand.exception;

public class UserNotMatchedException extends RuntimeException {
	
	public UserNotMatchedException() {
		super("권한이 없습니다.");
	}
}
