package io.gourmand.domain;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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

@Entity
public class Res {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RES_NUM")
	private Long res_num;
	
	@Column(name = "RES_NAME")
	private String resName;
	
	@Column(name = "X_VALUE")
	private BigDecimal xValue;
	
	@Column(name = "Y_VALUE")
	private BigDecimal yValue;
	
	@Column(name = "RES_HOUR")
	private String resHour;
	
	@Column(name = "TEL")
	private String tel;
	
	@Column(name = "AVG_STAR")
	private BigDecimal avgStar;
	
	@Column(name = "CATEGORY")
	private String category;
	
	@Column(name = "IMG_LINK")
	private String imgLink;

	@OneToOne
	@JoinColumn(name="ID")
	private RatingStandard ratingStandard;
	
	@OneToMany(mappedBy = "user")
	private List<Review> review;
}