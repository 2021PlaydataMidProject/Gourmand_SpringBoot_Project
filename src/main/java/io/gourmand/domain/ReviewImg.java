package io.gourmand.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="REVIEW_IMG")
public class ReviewImg {
	
	@Id
	@Column(name="REVIEW_IMG_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long reviewImgId; 
	
	@ManyToOne
	@JoinColumn(name="REVIEW_NUM")
	private Review review;
	
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
