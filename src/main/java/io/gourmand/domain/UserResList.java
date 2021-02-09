package io.gourmand.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity(name="USER_RES_LIST")
public class UserResList {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "LIST_NUM")
	private Long listNum;
	
	@ManyToOne
	@JoinColumn(name= "USER_NUM")
	private User user;
	
	@Column(name = "LIST_NAME")
	private String listName;
	
	@Column(name = "RES_ID")
	private Long resId;
}
