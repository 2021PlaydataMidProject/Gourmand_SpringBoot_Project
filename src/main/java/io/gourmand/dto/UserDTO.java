package io.gourmand.dto;

import io.gourmand.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserDTO {

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class SigninRequest {
		private String userId;
		private String pw;

		public static SigninRequest of(User user) {
			return SigninRequest.builder().userId(user.getUserId()).pw(user.getPw()).build();
		}
	}

	// 회원가입을 위한 DTO - 이미지 관련 추가, 날짜/드롭다운 등 변경 필요
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	@Builder
	public static class UserRegister {
		private String userId;
		private String pw;
		private String name;
		private String dob;
		private String job;
		private int pageStatus;

		public static User toEntity(UserRegister user) {
			return User.builder()
					.userId(user.getUserId())
					.pw(user.getPw())
					.name(user.getName())
					.dob(user.getDob())
					.job(user.getJob())
					.pageStatus(user.getPageStatus())
					.build();
		}
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class IdCheckResult {
		private boolean isExist;
	}
}
