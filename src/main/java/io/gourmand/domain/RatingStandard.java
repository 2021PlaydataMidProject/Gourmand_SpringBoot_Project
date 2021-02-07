package io.gourmand.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity(name="RATING_STANDARD")
public class RatingStandard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name= "R_FLAVOR")
	private BigDecimal rFlavor;
	
	@Column(name= "R_CLEAN")
	private BigDecimal rClean;
	
	@Column(name= "R_COST_VALUE")
	private BigDecimal rCostValue;
	
	@Column(name= "R_MOOD")
	private BigDecimal rMood;
	
	@Column(name= "R_KINDNESS")
	private BigDecimal rKindness;
	
	@Column(name= "R_ACCESS")
	private BigDecimal rAccess;
}