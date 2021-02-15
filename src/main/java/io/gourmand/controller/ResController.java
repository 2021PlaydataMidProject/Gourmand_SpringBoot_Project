package io.gourmand.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.gourmand.domain.Res;
import io.gourmand.domain.ResImg;
import io.gourmand.domain.User;
import io.gourmand.dto.ResDTO.ResInfo;
import io.gourmand.dto.ResDTO.ResRegister;
import io.gourmand.dto.ResDTO.ResThumbnail;
import io.gourmand.service.ResService;

@RestController
public class ResController {

	@Autowired
	private ResService resService;

	// 가게 정보를 담은 페이지
	@GetMapping("/res/{id}/resinfo")
	public ResInfo getResInfo(@PathVariable Long id) {
		return resService.getResInfo(id);
	}

	// Thumbnail 정보를 담은 페이지
	@GetMapping("/res/{id}/thumbnail")
	public ResThumbnail getResThumbnail(@PathVariable Long id) {
		return resService.getResThumbnail(id);
	}
	
	// 거리별(default)
	@GetMapping("/res/thumbnail/{xValue},{yValue}")
	public List<ResThumbnail> getAllResThumbnail(@PathVariable BigDecimal xValue, @PathVariable BigDecimal yValue){
		return resService.getAllRes(xValue, yValue);
	}
	
	// 카테고리별
	@GetMapping("/res/thumbnail/category/{category}/{xValue},{yValue}")
	public List<ResThumbnail> getResThumbnailByCategory(@PathVariable String category
			, @PathVariable BigDecimal xValue, @PathVariable BigDecimal yValue){
		return resService.getAllResByCategory(xValue, yValue, category);
	}
	
	// 별점순
	@GetMapping("/res/thumbnail/avgstar/{limit}/{xValue},{yValue}")
	public List<ResThumbnail> getResThumbnailByAvgStar(@PathVariable BigDecimal xValue, @PathVariable BigDecimal yValue
			, @PathVariable BigDecimal limit){
		return resService.getAllResByAvgStar(xValue, yValue, limit);
	}
	
	// 해당 가게를 리스트에 넣은 유저 반환
	@GetMapping("/res/{id}/user")
	public List<User> getUserByRes(@PathVariable Long id){
		return resService.getUserByRes(id);
	}
	
	// 가게 정보 저장
	@PostMapping("/res/regi")
	public void createRes(@RequestParam("resImg") List<MultipartFile> resImg, @RequestParam("res") String resRegi) {
		System.out.println(resRegi);
		ObjectMapper mapper = new ObjectMapper();
		try {
			Res res = resService.insertRes(mapper.readValue(resRegi, ResRegister.class));
			resImg.forEach(img -> {
				ResImg ri = resService.insertResImg(img, res);
				try {
					resService.saveImg(img, ri);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 가게 정보 수정
	@PostMapping("res/{id}/update")
	public void updateRes(@PathVariable Long id, @RequestBody ResRegister res) {
		resService.updateRes(id, res);
	}
	
	// 가게 정보 삭제
	@PostMapping("res/{id}/delete")
	public void deleteRes(@PathVariable Long id) {
		resService.deleteRes(id);
	}

}
