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

@Entity(name="USER_RES_LIST")
public class UserResList {
	@Id
	@GeneratedValue
	@Column(name = "LIST_NUM")
	private Long listNum;
	
	@ManyToOne
	@JoinColumn(name= "ID")
	private User user;
	
	@Column(name = "LIST_NAME")
	private String listName;
}
