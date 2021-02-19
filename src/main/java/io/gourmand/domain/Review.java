package io.gourmand.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Entity(name = "REVIEW")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REVIEW_NUM")
	private Long reviewNum;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "USER_NUM")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RES_NUM")
	private Res res;
	
	@Column(name = "FOOD_TYPE")
	private String foodType;
	
	@Column(name = "REVIEW")
	private String review;
	
	@Column(name = "WRITE_DATE")
	private LocalDate writeDate;
	
	@Column(name = "IMG_LINK")
	private String imgLink;
	
	@OneToOne
	@JoinColumn(name="RS_ID")
	private ReviewStandard reviewStandard;
	
	@OneToMany(mappedBy = "review")
	private List<ReviewImg> reviewImg;
	

	
}



