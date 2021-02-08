package io.gourmand.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	@ManyToOne
	@JoinColumn(name= "USER_ID")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "RES_NUM")
	private Res res;
	
	@Column(name = "FOOD_TYPE")
	private String foodType;
	
	@Column(name = "REVIEW")
	private String review;
	
	@Column(name = "WRITE_DAY")
	private LocalDateTime writeDate;
	
	@Column(name = "IMG_LINK")
	private String imgLink;
	
	@OneToOne
	@JoinColumn(name="ID")
	private ReviewStandard reviewStandard;
	
}
