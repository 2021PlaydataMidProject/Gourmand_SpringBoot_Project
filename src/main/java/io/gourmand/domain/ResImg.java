package io.gourmand.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="RES_IMG")
public class ResImg {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IMG_NUM")
	private Long imgNum;
	
	@Column(name="IMG_LINK")
	private String imgLink;
	
	@Column(name="IMG_NAME")
	private String imgName;
	
	@ManyToOne
	@JoinColumn(name = "RES_NUM")
	private Res resNum;
}
