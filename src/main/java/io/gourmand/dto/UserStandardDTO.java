package io.gourmand.dto;

import java.math.BigDecimal;

import io.gourmand.domain.UserStandard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class UserStandardDTO {

//	private Long id;
//	private BigDecimal uFlavor;
//	private BigDecimal uClean;
//	private BigDecimal uCost;
//	private BigDecimal uMood;
//	private BigDecimal uKindness;
//	private BigDecimal uAccess;
	
	@Getter	
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	@Builder
	public static class UserStandardRegister {
		private Long id;
		private BigDecimal uFlavor;
		private BigDecimal uClean;
		private BigDecimal uCost;
		private BigDecimal uMood;
		private BigDecimal uKindness;
		private BigDecimal uAccess;
		
		public static UserStandard toEntity(UserStandardRegister userStandard) {
			return UserStandard.builder()
					.uFlavor(userStandard.getUFlavor())
					.uClean(userStandard.getUClean())
					.uCost(userStandard.getUCost())
					.uMood(userStandard.getUMood())
					.uKindness(userStandard.getUKindness())
					.uAccess(userStandard.getUAccess())
					.build();
		}
	}
	
	@Getter	
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	@Builder
	public static class UserStandardRegistertest {
		private Long id;
		private String uFlavor;
		private String uClean;
		private String uCost;
		private String uMood;
		private String uKindness;
		private String uAccess;
		
		public static UserStandard toEntity(UserStandardRegistertest userStandard) {
			return UserStandard.builder()
					.uFlavor(new BigDecimal(userStandard.getUFlavor()))
					.uClean(new BigDecimal(userStandard.getUClean()))
					.uCost(new BigDecimal(userStandard.getUCost()))
					.uMood(new BigDecimal(userStandard.getUMood()))
					.uKindness(new BigDecimal(userStandard.getUKindness()))
					.uAccess(new BigDecimal(userStandard.getUAccess()))
					.build();
		}
	}
}

