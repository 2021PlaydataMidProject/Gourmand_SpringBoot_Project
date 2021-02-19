package io.gourmand.dto;

import java.math.BigDecimal;

import io.gourmand.domain.ReviewStandard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class ReviewStandardDTO {
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	@Builder
	public static class ReviewStandardRegister {
		private BigDecimal raccess;
		private BigDecimal rclean;
		private BigDecimal rcost;
		private BigDecimal rflavor;
		private BigDecimal rkindness;
		private BigDecimal rmood;

		public static ReviewStandard toEntity(ReviewStandardRegister reviewStandard) {
			return ReviewStandard.builder()
					.rAccess(reviewStandard.getRaccess())
					.rClean(reviewStandard.getRclean())
					.rCostValue(reviewStandard.getRcost())
					.rFlavor(reviewStandard.getRflavor())
					.rKindness(reviewStandard.getRkindness())
					.rMood(reviewStandard.getRmood()).build();
		}

	}
}
