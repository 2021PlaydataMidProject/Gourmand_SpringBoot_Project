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
		private BigDecimal uaccess;
		private BigDecimal uclean;
		private BigDecimal ucost;
		private BigDecimal uflavor;
		private BigDecimal ukindness;
		private BigDecimal umood;

		public static UserStandard toEntity(UserStandardRegister userStandard) {
			return UserStandard.builder().uAccess(userStandard.getUaccess()).uClean(userStandard.getUclean())
					.uCost(userStandard.getUcost()).uFlavor(userStandard.getUflavor())
					.uKindness(userStandard.getUkindness()).uMood(userStandard.getUmood()).build();
		}

	}
}
