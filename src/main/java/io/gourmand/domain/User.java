package io.gourmand.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

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

@Entity(name = "USER")
public class User  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "USER_NUM")
	private Long userNum;
	
	@Column(name = "USER_ID")
	private String userId;
	
	@OneToMany(mappedBy="to")
	private List<Followers> followers;

	@OneToMany(mappedBy="from")
	private List<Followers> following;
	
	@Column(name = "PW")
	private String pw;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DOB")
	private String dob;
	
	@Column(name = "JOB")
	private String job;
	
	@Column(name = "PAGE_STATUS")
	private int pageStatus;

	@Column(name = "SU_DATE")
	private LocalDate suDate;

	@OneToOne
	@JoinColumn(name = "US_ID")
	private UserStandard userStandard;
	
	@OneToMany(mappedBy = "replyNum", fetch=FetchType.LAZY)
	private List<Reply> reply;
	
	@OneToMany(mappedBy = "user", fetch=FetchType.LAZY)
	private List<UserImg> userImg;
	
	@OneToMany(mappedBy = "user", fetch=FetchType.LAZY)
	private List<ReviewLikes> reviewLikes;
	
	@OneToMany(mappedBy = "user", fetch=FetchType.LAZY)
	private List<ListLikes> listLikes;
	
	public User(String name) {
	    this.name = name;
	}
	

}





