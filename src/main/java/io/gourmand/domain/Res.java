package io.gourmand.domain;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; 
import javax.persistence.OneToMany;

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
	private Long resNum;

	@Column(name = "RES_NAME")
	private String resName;

	@Column(name = "X_VALUE", precision = 7, scale = 5)
	private BigDecimal xValue;

	@Column(name = "Y_VALUE", precision = 7, scale = 4)
	private BigDecimal yValue;

	@Column(name = "RES_HOUR")
	private String resHour;

	@Column(name = "TEL")
	private String tel;

	@Column(name = "AVG_STAR")
	private BigDecimal avgStar;

	@Column(name = "CATEGORY")
	private String category;

	@OneToMany(mappedBy = "res")
	private List<ResImg> resImg;

	@OneToMany(mappedBy = "res")

	private List<Review> review;
}