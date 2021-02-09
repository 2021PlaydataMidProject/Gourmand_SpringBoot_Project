package io.gourmand.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.gourmand.domain.Res;
import io.gourmand.dto.ResDTO;
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
		System.out.println("정보");
		return resService.getResInfo(id);
    }
	
	// Thumbnail 정보를 담은 페이지
	@GetMapping("/res/{id}/thumnail")
	public ResThumbnail getResThumnail(@PathVariable Long id, @RequestBody Res res) {
		System.out.println("썸네일");
		return resService.getResThumbnail(res);
	}
	
	// 가게 정보 저장
	@PostMapping("/res/regi")
	//public void createRes(@RequestBody ResRegister res) {
//	System.out.println(res.getResImg());
//	resService.insertRes(res);
	public void createRes(@RequestParam("resImg") List<MultipartFile> resImg, @RequestParam("res") String resRegi) {
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			ResRegister regi = mapper.readValue(resRegi, ResRegister.class);
			resService.insertRes(regi);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
}
