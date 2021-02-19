package io.gourmand.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

@Entity(name="REVIEW_IMG")
public class ReviewImg {
	
	@Id
	@Column(name="REVIEW_IMG_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long reviewImgId; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
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
	
	//60번째줄 filePath 끝부분     (+ review.getReviewNum() + "\\";) reviewNum으로 하실건지 확인 후 지정해주세요!!
	public static ReviewImg of(MultipartFile file, Review review) {
        String originFileName = file.getOriginalFilename();
        String ext = originFileName.substring(originFileName.lastIndexOf(".") + 1);
        String fileName = String.format("%s.%s", UUID.randomUUID().toString(), ext);
        String filePath = "C:\\MyGit\\midProject\\Gourmand_SpringBoot_Project\\src\\frontend\\public\\img\\rev\\";
        
        return ReviewImg.builder()
                .originName(originFileName)
                .name(fileName)
                .extension(ext)
                .size(file.getSize())
                .path(filePath)
                .review(review)
                .build();
	}	
}
