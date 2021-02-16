package io.gourmand.exception.common;

import lombok.Getter;

@Getter
public class BusinessException {
	   private String message;
	   
	    public String BusinessException(String message) {
	        return "아이디나 비밀번호가 맞지 않습니다";
	    }
}
