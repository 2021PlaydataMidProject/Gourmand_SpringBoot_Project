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

@Entity(name="LIST_LIKES")
public class ListLikes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LL_ID")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIST_NUM")
	private UserResList listNum;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "USER_NUM")
	private User user;
}
