package io.gourmand.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

import io.gourmand.dao.ReviewRepository;
import io.gourmand.dao.UserImgRepository;
import io.gourmand.dao.UserRepository;
import io.gourmand.dao.UserStandardRepository;
import io.gourmand.domain.Review;
import io.gourmand.domain.User;
import io.gourmand.domain.UserImg;
import io.gourmand.domain.UserResList;
import io.gourmand.domain.UserStandard;
import io.gourmand.dto.UserDTO.SigninRequest;
import io.gourmand.dto.UserDTO.SigninResponse;
import io.gourmand.dto.UserDTO.UserCountsInfo;
import io.gourmand.dto.UserDTO.UserRegister;
import io.gourmand.dto.UserDTO.UserThumbnail;
import io.gourmand.dto.UserStandardDTO.UserStandardRegister;
import io.gourmand.util.JwtUtil;

@Service
public class UserService {
	@Autowired
	UserRepository userDAO;
	@Autowired
	UserImgRepository userImgDAO;
	@Autowired
	UserStandardRepository userStandardDAO;
	@Autowired
	ReviewRepository revDAO;
	@Autowired
	JwtUtil jwtUtil;
	
	/**
	 * 아이디과 비밀번호가 일치하는 유저를 조회한다. 해당 아이디 유저가 존재하지 않거나 비밀번호가 일치하지 않으면 Exception
	 * 
	 * @param 아이디
	 * @param pw  비밀번호
	 * @return 아이디 비밀번호가 일치하는 유저
	 */
	public SigninResponse getMatchedUser(SigninRequest sign, HttpServletResponse res) {
		User signin = userDAO.findUserByUserId(sign.getUserId());
		// 없는 유저
		if (signin == null || !signin.getPw().equals(sign.getPw())) {
			return null;
		}
		
		final String token = jwtUtil.generateToken(signin);
		Cookie accessToken = CookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
		res.addCookie(accessToken);
		
		JsonObject obj =new JsonObject();
	    JsonObject data = new JsonObject();

	    data.addProperty("status", "success");
	    data.addProperty("message", "로그인에 성공했습니다.");
	    data.addProperty("token", token);
	    
	    obj.add("data", data);
		
	    System.out.println(obj.toString());
	    
		
		return SigninResponse.of(signin);
	}



	// 회원 1인 관련 정보페이지에 필요한 DTO를 생성해서 controller에 보낸다.
//	public UserInfo getUserInfo(Long userNum) {
//		Optional<User> user = userDAO.findById(userNum);
//		user.ifPresent(selectUser -> {
//			System.out.println(selectUser.getUserNum());
//		});
////		return UserInfo.of(userDAO.findById(userNum).get());
//	}

	// 팔로우, 팔로잉, 추천 계정 등에 들어갈 간략한 유저 정보 및 Thumbnail을 불러와 controller에 보낸다.
	public UserThumbnail getUserThumbnail(User user) {
		return UserThumbnail.of(userDAO.findById(user.getUserNum()).get());
	}

	// 회원 1인 탈퇴 - 얽혀있는 테이블이 많아서 null point exception이 많이 뜬다.0
//	public void deleteUser(User user) {
//		userStandardDAO.deleteById(user.getUserStandard().getId());
//		userDAO.deleteById(user.getUserId());
//		userImgDAO.deleteById(user.getUserNum());
//	}

	// 회원 가입시 회원 기준 저장
	public UserStandard insertUserStandard(UserStandardRegister userStandard) {
		return userStandardDAO.save(UserStandardRegister.toEntity(userStandard));
	}

	// 회원가입
	public User insertUser(UserRegister user, UserStandard userStandard) {
		return userDAO.save(UserRegister.toEntity(user, userStandard));
	};

	// MultipartFile -> entity -> SQL저장
	public UserImg insertUserImg(MultipartFile userImg, User user) {
		return userImgDAO.save(UserImg.of(userImg, user));
	}

	// MultipartFile -> 저장
	public void saveImg(MultipartFile file, UserImg user) throws IOException {
		// parent directory를 찾는다.
		Path directory = Paths.get(user.getPath()).toAbsolutePath().normalize();

		// directory 해당 경로까지 디렉토리를 모두 만든다.
		Files.createDirectories(directory);

		// 파일명을 바르게 수정한다.
		String fileName = StringUtils.cleanPath(user.getName());

		// 파일명에 '..' 문자가 들어 있다면 오류를 발생하고 아니라면 진행(해킹및 오류방지)
		Assert.state(!fileName.contains(".."), "Name of file cannot contain '..'");
		// 파일을 저장할 경로를 Path 객체로 받는다.
		Path targetPath = directory.resolve(fileName).normalize();

		// 파일이 이미 존재하는지 확인하여 존재한다면 오류를 발생하고 없다면 저장한다.
		Assert.state(!Files.exists(targetPath), fileName + " File alerdy exists.");
		file.transferTo(targetPath);
	}

	// 해당 아이디의유저가 매긴 리뷰개수를 반환한다.
	public UserCountsInfo getUserReviewCounts(Review reviewNum) {
		userDAO.findcountbyReviewNum(reviewNum);
		return getUserReviewCounts(reviewNum);
	}

	// 해당아이디가 작성한 리스트의 개수를 반환한다.
	public UserCountsInfo getUserListCounts(UserResList listNum) {
		userDAO.findcountByUserList(listNum);
		return getUserListCounts(listNum);
	}
}

//	/**
//	    * 수정할 수 있는 유저정보를 수집한다.
//	    * 파라미터 값이 비어 있으면 그 항목은 수정에서 제외한다.
//	    * user_id 존재하지 않으면 exception
//	    * @param userId
//	    * @param userInfo 유저 수정 정보 
//	    * 
//	    * 이름 수정 가능? 
//	    */
//	   public void editUserInfo(String userId, UserDTO.UserInfoEditRequest userInfo) {
//	      User user;
//	      
//	      if (userInfo.getName() != null) user.setName(userInfo.getName());
//	      if (userInfo.getPw() != null)    user.setPw(userInfo.getPw());
//	      if (userInfo.getJob() != null) user.setJob(userInfo.getJob());
//	    
//	      if (userInfo.getPageStatus() != null) user.setPageStatus(userInfo.getPageStatus());
//	      //USERSTANDARD를 변경할 수 있는지? 
////	      if (userInfo.getUserStandard != null) user.setUserStandard(userInfo.getUserStandard());
//	   }

// 마이페이지 - 내 리뷰 확인 - 더보기 - 시간순 정렬 - 자바스트림... 모름
// 시간순으로 리뷰 리스트를 반환한다.
//	      @Transactional
//	       public List<RevDTO> findByPageRequest(PageRequest pageRequest) {
//	           return reviewDAO.findAll(pageRequest).stream()
//	                   .map(RevDTO::new)
//	                   .collect(Collectors.toList());
//	       }
//	      
//	      //역시간순으로 리뷰 리스트를 반환한다. 
//	      @Transactional
//	       public List<RevDTO> findByPageRequestReverse(PageRequest pageRequest) {
//	           return reviewDAO.findAllByOrderByIdDesc(pageRequest).stream()
//	                   .map(RevDTO::new)
//	                   .collect(Collectors.toList());
//	       }
//}
