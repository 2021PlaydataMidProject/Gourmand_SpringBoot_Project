package io.gourmand.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import io.gourmand.dao.ResImgRepository;
import io.gourmand.dao.ResRepository;
import io.gourmand.domain.Res;
import io.gourmand.domain.ResImg;
import io.gourmand.dto.ResDTO.ResInfo;
import io.gourmand.dto.ResDTO.ResRegister;
import io.gourmand.dto.ResDTO.ResThumbnail;

@Service
public class ResService {

	@Autowired
	ResRepository resDAO;
	@Autowired
	ResImgRepository resImgDAO;

	// 가게 정보페이지에 필요한 DTO를 생성해서 controller에 보낸다.
	public ResInfo getResInfo(Long id) {
		return ResInfo.of(resDAO.findById(id).get());
	}

	// Thumnail을 불러와 controller에 보낸다.
	public ResThumbnail getResThumbnail(Res res) {
		return ResThumbnail.of(resDAO.findById(res.getResNum()).get());
	}

	// 가게 등록 페이지에서 저장
	public Res insertRes(ResRegister res) {
		return resDAO.save(ResRegister.toEntity(res));
	}
	
	//MultipartFile -> entity -> SQL저장
	public ResImg insertResImg(MultipartFile resImg, Res res){
		return resImgDAO.save(ResImg.of(resImg, res));
	}
	
	//MultipartFile -> 저장
	public void saveImg(MultipartFile file, ResImg res) throws IOException {
		// parent directory를 찾는다.
		Path directory = Paths.get(res.getPath()).toAbsolutePath().normalize();

		// directory 해당 경로까지 디렉토리를 모두 만든다.
		Files.createDirectories(directory);

		// 파일명을 바르게 수정한다.
		String fileName = StringUtils.cleanPath(res.getName());

		// 파일명에 '..' 문자가 들어 있다면 오류를 발생하고 아니라면 진행(해킹및 오류방지)
		Assert.state(!fileName.contains(".."), "Name of file cannot contain '..'");
		// 파일을 저장할 경로를 Path 객체로 받는다.
		Path targetPath = directory.resolve(fileName).normalize();

		// 파일이 이미 존재하는지 확인하여 존재한다면 오류를 발생하고 없다면 저장한다.
		Assert.state(!Files.exists(targetPath), fileName + " File alerdy exists.");
		file.transferTo(targetPath);
	}
}
