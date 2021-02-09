package io.gourmand.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.gourmand.dao.ResRepository;
import io.gourmand.domain.Res;
import io.gourmand.dto.ResDTO.ResInfo;
import io.gourmand.dto.ResDTO.ResRegister;
import io.gourmand.dto.ResDTO.ResThumbnail;

@Service
public class ResService {
	
	@Autowired
	ResRepository resDAO;
	
	
	// 가게 정보페이지에 필요한 DTO를 생성해서 controller에 보낸다.
	public ResInfo getResInfo(Long id) {
		return ResInfo.of(resDAO.findById(id).get());
	}
	
	// Thumnail을 불러와 controller에 보낸다.
	public ResThumbnail getResThumbnail(Res res) {
		return ResThumbnail.of(resDAO.findById(res.getResNum()).get());
	}
	
	// 가게 등록 페이지에서 저장
	public void insertRes(ResRegister res) {
		resDAO.save(ResRegister.toEntity(res));
	}
}
