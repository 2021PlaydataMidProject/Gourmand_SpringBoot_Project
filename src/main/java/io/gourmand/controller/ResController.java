package io.gourmand.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import io.gourmand.dto.ResDTO.ResInfo;
import io.gourmand.dto.ResDTO.ResRegister;
import io.gourmand.dto.ResDTO.ResThumbnail;
import io.gourmand.dto.UserDTO.UserThumbnail;
import io.gourmand.service.ResService;
import io.gourmand.util.CookieUtil;
import io.gourmand.util.JwtUtil;
import io.gourmand.util.NaverGeoCoding;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ResController {

	@Autowired
	private ResService resService;

	@Value("${jwt.secret}")
	private String secret;

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
	@GetMapping("/res/thumbnail/{xValue},{yValue}/{limit}")
	public List<ResThumbnail> getAllResThumbnail(@PathVariable BigDecimal xValue, @PathVariable BigDecimal yValue,
			@PathVariable Double limit) {
		return resService.getAllRes(xValue, yValue, limit);
	}

	// 카테고리별
	@GetMapping("/res/thumbnail/category/{category}/{xValue},{yValue}")
	public List<ResThumbnail> getResThumbnailByCategory(@PathVariable String category, @PathVariable BigDecimal xValue,
			@PathVariable BigDecimal yValue) {
		return resService.getAllResByCategory(xValue, yValue, category);
	}

	// 별점순
	@GetMapping("/res/thumbnail/avgstar/{limit}/{xValue},{yValue}")
	public List<ResThumbnail> getResThumbnailByAvgStar(@PathVariable BigDecimal xValue, @PathVariable BigDecimal yValue,
			@PathVariable BigDecimal limit) {
		return resService.getAllResByAvgStar(xValue, yValue, limit);
	}

	// 이름 검색
	@GetMapping("/res/thumbnail/search/{name}/{xValue},{yValue}/{limit}")
	public List<ResThumbnail> getResThumbnailByResName(@PathVariable BigDecimal xValue, @PathVariable BigDecimal yValue,
			@PathVariable Double limit, @PathVariable String name) {
		log.info("음식점 검색 : " + name);
		return resService.returnAllResByName(xValue, yValue, limit, name);
	}

	// 지역 검색
	@GetMapping("/res/thumbnail/region/{region}/{limit}")
	public List<ResThumbnail> getResThumbnailByLocation(@PathVariable Double limit, @PathVariable String region) {
		log.info("지역 검색 : " + region);
		BigDecimal[] axis = NaverGeoCoding.returnAxis(region);
		System.out.println(axis[0] + " " + axis[1]);
		return resService.getAllRes(axis[1], axis[0], limit);
	}

	// 해당 가게를 리스트에 넣은 유저 반환
	@GetMapping("/res/{id}/user")
	public List<UserThumbnail> getUserByRes(@PathVariable Long id) {
		return resService.getUserByRes(id);
	}

	// 해당 유저의 모든 리스트 맵으로 반환
	@GetMapping("/res/user/{id}/list")
	public Map<String, List<ResThumbnail>> getResOfList(@PathVariable Long id) {
		Map<String, List<ResThumbnail>> resList = new HashMap<>();
		resService.getResListName(id).forEach(name -> resList.put(name, resService.getAllResOfList(id, name)));
		return resList;
	}

	// reslist 추가
	@PostMapping("/res/user/insert")
	public void insetResToUser(@RequestParam("res") String resNum, @RequestParam("listName") String listName,
			HttpServletRequest hsp) {
		Claims claim = new JwtUtil(secret).getClaims(CookieUtil.getCookie(hsp, "accessToken").getValue());
		Long userNum = claim.get("user_num", Long.class);
		log.info("회원리스트에 음식점 추가 : 회원," + userNum + ",음식점," + resNum);
		resService.insertResList(listName, Long.valueOf(resNum), userNum);
	}

	// 가게 정보 저장
	@PostMapping("/res/regi")
	public void createRes(@RequestParam("resImg") List<MultipartFile> resImg, @RequestParam("res") String resRegi) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Res res = resService.insertRes(mapper.readValue(resRegi, ResRegister.class));
			log.info("음식점 추가 : " + res.getResName());
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

	// 가게 이미지만 추가
	@PostMapping("/res/{id}/update/img")
	public void insertImgOfRes(@PathVariable("id") Long id, @RequestParam("resImg") List<MultipartFile> resImg) {
		resImg.forEach(img -> {
			ResImg ri = resService.insertResImg(img, id);
			log.info("가게 이미지 추가 : 가게," + id + ",이미지명," + ri.getName());
			try {
				resService.saveImg(img, ri);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	// 가게 정보 수정
	@PostMapping("res/{id}/update")
	public void updateRes(@PathVariable Long id, @RequestBody ResRegister res) {
		log.info("가게 정보 수정 : "+ id);
		resService.updateRes(id, res);
	}

	// 가게 정보 삭제
	@PostMapping("res/{id}/delete")
	public void deleteRes(@PathVariable Long id) {
		log.info("가게 정보 삭제 : "+ id);
		resService.deleteRes(id);
	}

}
