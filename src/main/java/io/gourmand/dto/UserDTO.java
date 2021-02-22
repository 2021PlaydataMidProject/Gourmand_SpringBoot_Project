package io.gourmand.dto;

import java.time.LocalDate;
import java.util.List;

import io.gourmand.domain.Review;
import io.gourmand.domain.User;
import io.gourmand.domain.UserImg;
import io.gourmand.domain.UserResList;
import io.gourmand.domain.UserStandard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class UserDTO {

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	@Builder
	public static class SigninRequest {
		private String userId;
		private String pw;
		private Long userNum;

		public static SigninRequest of(User user) {
			return SigninRequest.builder().userId(user.getUserId()).pw(user.getPw()).build();
		}
	}
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	@Builder
	public static class SigninResponse {
		
		private Long userNum;
		private String userId;
		private String name;

		public static SigninResponse of(User user) {
			return SigninResponse.builder().userNum(user.getUserNum()).userId(user.getUserId()).name(user.getName()).build();
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
		private LocalDate suDate;

		public static User toEntity(UserRegister user, UserStandard userStandard) {
			return User.builder().userId(user.getUserId()).pw(user.getPw()).name(user.getName()).dob(user.getDob())
					.job(user.getJob()).suDate(LocalDate.now()).pageStatus(user.getPageStatus())
					.userStandard(userStandard).build();
		}
	}

	// 회원 1인의 전체 정보 불러오기 - // thread 컬럼명 Reply로 변경
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class UserInfo {
		private String userId;
		private String name;
		private String dob;
		private String job;
		private int pageStatus;
		private LocalDate suDate;
		private UserStandard userStandard;
		private List<UserImg> userImg;
		private int revCnt;

		public static UserInfo of(User user) {
			return UserInfo.builder()
					.userId(user.getUserId())
					.dob(user.getDob())
					.name(user.getName())
					.job(user.getJob())
					.pageStatus(user.getPageStatus())
					.suDate(user.getSuDate())
					.userStandard(user.getUserStandard())
					.userImg(user.getUserImg())
					.revCnt(user.getReview().size()).build();
		}
	}

	// 회원 이미지와 간단 설명 - 근데 리뷰수, LIKE수는 어떻게 카운트하지???
	@NoArgsConstructor
	@AllArgsConstructor
	@Setter
	@Getter
	@Builder
	public static class UserThumbnail {
		private Long userNum;
		private String userId;
		private String name;
		private int pageStatus;
		private UserImg userImg;

		public static UserThumbnail of(User user) {
			UserImg umg = null;
			if (user.getUserImg().size() > 0) {
				umg = user.getUserImg().get(0);
			}
			return UserThumbnail.builder().userNum(user.getUserNum()).userId(user.getUserId()).name(user.getName())
					.pageStatus(user.getPageStatus()).userImg(umg)
					// 대표 이미지 선택방법 고려해야함
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

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class UserCountsInfo {
		private User userId;
		private Review reviewNum;
		private UserResList listNum;

	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class	UserSimple {
		private Long userNum;
		private String userName;

	}
}
