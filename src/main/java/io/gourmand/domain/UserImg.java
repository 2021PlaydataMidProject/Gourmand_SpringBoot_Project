package io.gourmand.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="USER_IMG")
public class UserImg {
	
	@Id
	@Column(name="RES_IMG_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long resImgId; 
	
	@ManyToOne
	@JoinColumn(name="USER_NUM")
	private User user;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="PATH")
	private String path;
	
	@Column(name="SIZE")
	private Long size;
	
	@Column(name="EXTENSION")
	private String extension;
	
	@Column(name="ORIGIN_NAME")
	private String originName;
}