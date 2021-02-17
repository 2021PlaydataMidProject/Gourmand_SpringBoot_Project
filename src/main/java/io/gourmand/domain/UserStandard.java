package io.gourmand.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity(name = "USER_STANDARD")
public class UserStandard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "US_ID")
	private Long id;
	
	@Column(name = "U_FLAVOR", precision = 2, scale = 1)
	private BigDecimal uFlavor;
	
	@Column(name = "U_CLEAN", precision = 2, scale = 1)
	private BigDecimal uClean;
	
	@Column(name = "U_COST", precision = 2, scale = 1)
	private BigDecimal uCost;
	
	@Column(name = "U_MOOD", precision = 2, scale = 1)
	private BigDecimal uMood;
	
	@Column(name = "U_KINDNESS", precision = 2, scale = 1)
	private BigDecimal uKindness;
	
	@Column(name = "U_ACCESS", precision = 2, scale = 1)
	private BigDecimal uAccess;
}
