package io.gourmand.service;

import java.io.IOException;
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

import io.gourmand.dao.ResRepository;
import io.gourmand.dao.ReviewImgRepository;
import io.gourmand.dao.ReviewRepository;
import io.gourmand.dao.ReviewStandardRepository;
import io.gourmand.dao.UserRepository;
import io.gourmand.domain.Review;
import io.gourmand.domain.ReviewImg;
import io.gourmand.domain.ReviewStandard;
import io.gourmand.dto.RevDTO.RevInfo;
import io.gourmand.dto.RevDTO.RevRegister;
import io.gourmand.dto.RevDTO.ReviewThumbnail;
import io.gourmand.dto.ReviewStandardDTO.ReviewStandardRegister;

@Service
public class RevService {

	@Autowired
	private ReviewRepository revDAO;
	@Autowired
	private ReviewStandardRepository rsDAO;
	@Autowired
	private UserRepository userDAO;
	@Autowired
	private ResRepository resDAO;
	@Autowired
	private ReviewImgRepository revImgDAO;

	// 모든 댓글 조회
	public List<Review> getReviewList(Review review) {
		return (List<Review>) revDAO.findAll();
	}

	// 하나의 댓글 조회
	public RevInfo getReview(Review review) {
		return RevInfo.of(revDAO.findById(review.getReviewNum()).get());
	}

	// 댓글 삭제
	public void deleteReview(Review review) {
		revDAO.deleteById(review.getReviewNum());
	}

	// 댓글 등록
	public Review insertRev(RevRegister rev, Long user, Long res, ReviewStandard rs) {
		return revDAO.save(RevRegister.toEntity(rev, userDAO.findById(user).get(), resDAO.findById(res).get(), rs));
	}
	
	public ReviewStandard insertRevSt(ReviewStandardRegister rev) {
		return rsDAO.save(ReviewStandardRegister.toEntity(rev));
	}

	// 댓글 수정
	public void updateReview(Review review) {
		Review findReview = revDAO.findById(review.getReviewNum()).get();

		findReview.setReview(review.getReview());
		findReview.setReviewImg(review.getReviewImg());
		revDAO.save(findReview);
	}

	// 가게에 대한 댓글Thumbnail 반환
	public List<ReviewThumbnail> getRevThumbnailByResNum(Long resNum) {
		List<ReviewThumbnail> revt = new ArrayList<>();
		revDAO.findAllByResNum(resNum).forEach(r -> revt.add(ReviewThumbnail.of(r)));
		return revt;
	}

	// 가게의 reviewstandard
	public String getResAvgRatingStandard(Long id) {
		return rsDAO.getResAvgStandard(id);
	}

	// 리뷰 역시간순으로 조회
	public List<ReviewThumbnail> getRevOrderByTime() {
		List<ReviewThumbnail> rt = new ArrayList<>();
		revDAO.findAllOrderByDate().forEach(rev -> rt.add(ReviewThumbnail.of(rev)));
		return rt;
	}

	// MultipartFile -> entity -> SQL저장
	public ReviewImg insertRevImg(MultipartFile revImg, Review rev) {
		return revImgDAO.save(ReviewImg.of(revImg, rev));
	}

	// MultipartFile -> 저장
	public void saveImg(MultipartFile file, ReviewImg rev) throws IOException {
		// parent directory를 찾는다.
		Path directory = Paths.get(rev.getPath()).toAbsolutePath().normalize();

		// directory 해당 경로까지 디렉토리를 모두 만든다.
		Files.createDirectories(directory);

		// 파일명을 바르게 수정한다.
		String fileName = StringUtils.cleanPath(rev.getName());

		// 파일명에 '..' 문자가 들어 있다면 오류를 발생하고 아니라면 진행(해킹및 오류방지)
		Assert.state(!fileName.contains(".."), "Name of file cannot contain '..'");
		// 파일을 저장할 경로를 Path 객체로 받는다.
		Path targetPath = directory.resolve(fileName).normalize();

		// 파일이 이미 존재하는지 확인하여 존재한다면 오류를 발생하고 없다면 저장한다.
		Assert.state(!Files.exists(targetPath), fileName + " File alerdy exists.");
		file.transferTo(targetPath);
	}

}

// 댓글 등록 
//public Review addReview(Long reviewNum, String username,String resname,String foodType,
//						Review description,LocalDate writeDate,String imgLink,
//						ReviewStandard reviewStandard,ReviewImg reviewImg,
//						@RequestBody Review rev) {
//	
//	Review review = Review.builder()
//  		.reviewNum(reviewNum)
//  		.user(userDAO.findUserByName(username))
//  		.res(resDAO.findResByName(resname))
//  		.foodType(foodType)
//  		.review(rev.getReview())
//  		.writeDate(writeDate)
//  		.imgLink(imgLink)
//  		.reviewStandard(rev.getReviewStandard())
//  		.reviewImg(rev.getReviewImg())
//  		.build();
//
//  return revDAO.save(review);
//}

