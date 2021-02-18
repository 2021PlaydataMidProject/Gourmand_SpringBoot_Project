package io.gourmand.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
	private String userId;
	private String pw;
}