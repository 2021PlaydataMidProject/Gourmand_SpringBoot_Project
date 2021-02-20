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

@Entity(name="RES_IMG")
public class ResImg {
	
	@Id
	@Column(name="RES_IMG_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long resImgId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="RES_NUM")
	private Res res;
	
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
	
	public static ResImg of(MultipartFile file, Res res) {
        String originFileName = file.getOriginalFilename();
        String ext = originFileName.substring(originFileName.lastIndexOf(".") + 1);
        String fileName = String.format("%s.%s", UUID.randomUUID().toString(), ext);
        String filePath = "src\\frontend\\public\\img\\res\\";
        
        return ResImg.builder()
                .originName(originFileName)
                .name(fileName)
                .extension(ext)
                .size(file.getSize())
                .path(filePath)
                .res(res)
                .build();
    }
}
