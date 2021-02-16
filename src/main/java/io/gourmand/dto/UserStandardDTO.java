package io.gourmand.dto;

import java.math.BigDecimal;

import io.gourmand.domain.UserStandard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}

