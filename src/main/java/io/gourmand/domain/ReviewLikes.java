package io.gourmand.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

@Entity(name="REVIEW_LIKES")
public class ReviewLikes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="RL_ID")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="REVIEW_NUM")
	private Review review;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="USER_NUM")
	private User user;
	
	
}