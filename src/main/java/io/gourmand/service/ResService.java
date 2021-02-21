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
import io.gourmand.dao.UserResListRepository;
import io.gourmand.domain.Res;
import io.gourmand.domain.ResImg;
import io.gourmand.domain.UserResList;
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
	@Autowired
	UserResListRepository urlDAO;

	// 가게 정보페이지에 필요한 DTO를 생성해서 controller에 보낸다.
	public ResInfo getResInfo(Long id) {
		return ResInfo.of(resDAO.findById(id).get());
	}

	// Thumnail을 불러와 controller에 보낸다.
	public ResThumbnail getResThumbnail(Long id) {
		return ResThumbnail.of(resDAO.findById(id).get());
	}

	public List<ResThumbnail> getAllRes(BigDecimal xValue, BigDecimal yValue, Double limit) {
		List<ResThumbnail> resThumbList = new ArrayList<>();
		resDAO.findAllOrderByAxis(xValue, yValue, BigDecimal.valueOf(limit/103.585)).forEach(r -> resThumbList.add(ResThumbnail.of(r)));
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
	
	// 해당 가게를 리스트에 넣은 유저 반환
	public List<UserThumbnail> getUserByRes(Long id) {
		List<UserThumbnail> userThumbList = new ArrayList<>();
		userDAO.findUsersOfRes(id).forEach(user -> userThumbList.add(UserThumbnail.of(user)));
		return userThumbList;
	}
	
	// 유저에 대한 리스트 이름들 반환
	public List<String> getResListName(Long id){
		return urlDAO.findListNamebyUser(id);
	}
	
	// 특정 리스트에 대한 가게 정보 반환
	public List<ResThumbnail> getAllResOfList(Long id, String name){
		List<ResThumbnail> resThumbList = new ArrayList<>();
		urlDAO.findAllbyUserAndListName(id, name).forEach(r -> resThumbList.add(ResThumbnail.of(r.getRes())));
		return resThumbList;
	}
	
	// UserResList 추가
	public void insertResList(String listName, Long resNum, Long userNum) {
		urlDAO.save(UserResList.builder().listName(listName).res(resDAO.findById(resNum).get()).user(userDAO.findById(userNum).get()).build());
	}
	
	
	public void updateResAvgStar(Long res) {
		resDAO.updateResAvgStar(res);
	}
	
	// 가게 등록 페이지에서 저장
	public Res insertRes(ResRegister res) {
		return resDAO.save(ResRegister.toEntity(res));
	}

	public void updateRes(Long id, ResRegister resRegi) {
		Res newres = resDAO.findById(id).get();
		Res res = ResRegister.toEntity(resRegi);
		
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
	
	// MultipartFile -> entity -> SQL저장
		public ResImg insertResImg(MultipartFile resImg, Long res) {
			return resImgDAO.save(ResImg.of(resImg, resDAO.findById(res).get()));
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
