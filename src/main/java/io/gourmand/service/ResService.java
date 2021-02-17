package io.gourmand.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import io.gourmand.dao.ResImgRepository;
import io.gourmand.dao.ResRepository;
import io.gourmand.dao.UserRepository;
import io.gourmand.domain.Res;
import io.gourmand.domain.ResImg;
import io.gourmand.dto.ResDTO.ResInfo;
import io.gourmand.dto.ResDTO.ResRegister;
import io.gourmand.dto.ResDTO.ResThumbnail;
import io.gourmand.dto.UserDTO.UserThumbnail;

@Service
public class ResService {

	@Autowired
	ResRepository resDAO;
	@Autowired
	ResImgRepository resImgDAO;
	@Autowired
	UserRepository userDAO;

	// 가게 정보페이지에 필요한 DTO를 생성해서 controller에 보낸다.
	public ResInfo getResInfo(Long id) {
		return ResInfo.of(resDAO.findById(id).get());
	}

	// Thumnail을 불러와 controller에 보낸다.
	public ResThumbnail getResThumbnail(Long id) {
		return ResThumbnail.of(resDAO.findById(id).get());
	}

	// 전체 가게 -> 거리순
<<<<<<< HEAD
	public List<ResThumbnail> getAllRes(BigDecimal xValue, BigDecimal yValue, BigDecimal limit) {
		List<ResThumbnail> resThumbList = new ArrayList<>();
		resDAO.findAllOrderByAxis(xValue, yValue, limit).forEach(r -> resThumbList.add(ResThumbnail.of(r)));
=======
	public List<ResThumbnail> getAllRes(BigDecimal xValue, BigDecimal yValue, Double limit) {
		List<ResThumbnail> resThumbList = new ArrayList<>();
		resDAO.findAllOrderByAxis(xValue, yValue, BigDecimal.valueOf(limit/103.585)).forEach(r -> resThumbList.add(ResThumbnail.of(r)));
>>>>>>> 00fdd0bf3b8cda7e556b4d6ec446853e6b47a624
		return resThumbList;
	}

	// 카테고리(음식종류) 별 반환 (거리순)
	public List<ResThumbnail> getAllResByCategory(BigDecimal xValue, BigDecimal yValue, String category) {
		List<ResThumbnail> resThumbList = new ArrayList<>();
		resDAO.findAllbyCategory(category, xValue, yValue).forEach(r -> resThumbList.add(ResThumbnail.of(r)));
		return resThumbList;
	}

	// 평균 평점별 반환
	public List<ResThumbnail> getAllResByAvgStar(BigDecimal xValue, BigDecimal yValue, BigDecimal limit) {
		List<ResThumbnail> resThumbList = new ArrayList<>();
		resDAO.findAllbyAvgStar(xValue, yValue, limit).forEach(r -> resThumbList.add(ResThumbnail.of(r)));
		return resThumbList;
	}

	// 가게 검색
	public List<ResThumbnail> returnAllResByName(BigDecimal xValue, BigDecimal yValue, Double limit, String name){
		List<ResThumbnail> resThumbList = new ArrayList<>();
		resDAO.findAllbyResName(xValue, yValue, BigDecimal.valueOf(limit/103.585), name).forEach(r -> resThumbList.add(ResThumbnail.of(r)));
		return resThumbList;
	}
	
	// 해당 가게를 리스트에 넣은 유저 반환 - res 팀 작업 중
	public List<UserThumbnail> getUserByRes(Long id) {
		List<UserThumbnail> userThumbList = new ArrayList<>();
		userDAO.findUsersOfRes(id).forEach(user -> userThumbList.add(UserThumbnail.of(user)));
		return userThumbList;
	}

	// 가게 등록 페이지에서 저장
	public Res insertRes(ResRegister res) {
		return resDAO.save(ResRegister.toEntity(res));
	}

	public void updateRes(Long id, ResRegister resRegi) {
		Res newres = resDAO.findById(id).get();
		Res res = ResRegister.toEntity(resRegi);
		
		newres.setResHour(res.getResHour());
		newres.setResAddress(res.getResAddress());
		newres.setResName(res.getResName());
		newres.setTel(res.getTel());
		newres.setCategory(res.getCategory());
		
		resDAO.save(newres);
	}

	public void deleteRes(Long id) {
		resDAO.deleteById(id);
	}

	// MultipartFile -> entity -> SQL저장
	public ResImg insertResImg(MultipartFile resImg, Res res) {
		return resImgDAO.save(ResImg.of(resImg, res));
	}

	// MultipartFile -> 저장
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
