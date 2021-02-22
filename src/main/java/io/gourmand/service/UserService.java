package io.gourmand.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

import io.gourmand.dao.ReviewRepository;
import io.gourmand.dao.UserImgRepository;
import io.gourmand.dao.UserRepository;
import io.gourmand.dao.UserStandardRepository;
import io.gourmand.domain.User;
import io.gourmand.domain.UserImg;
import io.gourmand.domain.UserStandard;
import io.gourmand.dto.RevDTO.ReviewThumbnail;
import io.gourmand.dto.UserDTO.SigninRequest;
import io.gourmand.dto.UserDTO.SigninResponse;
import io.gourmand.dto.UserDTO.UserInfo;
import io.gourmand.dto.UserDTO.UserRegister;
import io.gourmand.dto.UserDTO.UserSimple;
import io.gourmand.dto.UserDTO.UserThumbnail;
import io.gourmand.dto.UserStandardDTO.UserStandardRegister;
import io.gourmand.exception.PasswordWrongException;
import io.gourmand.exception.UserExistedException;
import io.gourmand.exception.UserIdNotExistedException;
import io.gourmand.util.CookieUtil;
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

	PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userDAO = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public SigninResponse getMatchedUser(SigninRequest sign, HttpServletResponse res) {
		User signin = userDAO.findUserByUserId(sign.getUserId());
		// 없는 유저
		if (signin == null || !signin.getPw().equals(sign.getPw())) {
			return null;
		}

		final String token = jwtUtil.generateToken(signin);
		Cookie accessToken = CookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
		res.addCookie(accessToken);

		JsonObject obj = new JsonObject();
		JsonObject data = new JsonObject();

		data.addProperty("status", "success");
		data.addProperty("message", "로그인에 성공했습니다.");
		data.addProperty("token", token);

		obj.add("data", data);

		System.out.println(obj.toString());

		return SigninResponse.of(signin);
	}

	public void logout(HttpServletResponse res) {
		Cookie deleteToken = CookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, null);
		deleteToken.setMaxAge(0);
		res.addCookie(deleteToken);
	}

   // 회원 정보 수정
 	public User updateUser(UserRegister user, UserStandardRegister userStandard, Long userNum) {
 		User us = userDAO.findByUserNum(userNum); //
 		us.setName(user.getName());
 		us.setPw(user.getPw());
 		us.setDob(user.getDob());
 		us.setJob(user.getJob());
 		us.setPageStatus(user.getPageStatus());
 		
 		UserStandard userSt = us.getUserStandard();
 		userSt.setUAccess(userStandard.getUaccess());
 		userSt.setUClean(userStandard.getUclean());
 		userSt.setUCost(userStandard.getUcost());
 		userSt.setUFlavor(userStandard.getUflavor());
 		userSt.setUKindness(userStandard.getUkindness());
 		userSt.setUMood(userStandard.getUmood());
 		
 		return us;
 	}
 	
 	
 	// 회원 이미지 삭제
	public void deleteUserImg(Long id) {
		userImgDAO.deleteById(id);
	}

	// 회원 삭제
	public void deleteUser(Long userNum) {
		User us = userDAO.findByUserNum(userNum); 
		us.getUserImg().forEach(img -> userImgDAO.delete(img));
		userDAO.delete(us);
		}	
	
	// 회원 정보 삭제
	public void deleteUserStandard(Long id) {
		userStandardDAO.deleteById(id);
		}	
	
	// 회원 정보 조회
	public Optional<User> getUser(Long userNum) {
		return userDAO.findById(userNum);
	}

	// 회원 1인 관련 정보페이지에 필요한 DTO를 생성해서 controller에 보낸다.
	public UserInfo getUserInfo(Long userNum) {
		return UserInfo.of(userDAO.findById(userNum).get());
	}

	public User registerUser(String dob, String job, int pageStatus, LocalDate suDate, UserStandard userStandard,
			String userId, String name, String pw) {
		Optional<User> existed = userDAO.findByUserId(userId);
		if (existed.isPresent()) {
			throw new UserExistedException(userId);
		}

		String encodedPassword = passwordEncoder.encode(pw);
		User user = User.builder().userNum(1004L).dob(dob).job(job).pageStatus(pageStatus).suDate(suDate)
				.userStandard(userStandard).userId(userId).name(name).pw(encodedPassword).build();

		return userDAO.save(user);
	}


	// 팔로우, 팔로잉, 추천 계정 등에 들어갈 간략한 유저 정보 및 Thumbnail을 불러와 controller에 보낸다.
	public UserThumbnail getUserThumbnail(User user) {
		return UserThumbnail.of(userDAO.findById(user.getUserNum()).get());
	}

	// 팔로워 많은 유저 3명 반환 (0 아닐시)
	public List<UserSimple> getFamousUsers() {
		int cnt = 0;
		List<UserSimple> users = new ArrayList<>();
		for (User user : userDAO.findUsersByFollwerCount()) {
			if (++cnt <= 2 || user.getFollowers().size() > 0) {
				users.add(UserSimple.builder().userNum(user.getUserNum()).userName(user.getName()).build());
			}
		}
		return users;
	}

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

	public User authenticate(String userId, String password) {
		User user = ((Optional<User>) userDAO.findByUserId(userId))
				.orElseThrow(() -> new UserIdNotExistedException(userId));

		if (!passwordEncoder.matches(password, user.getPw())) {
			throw new PasswordWrongException();
		}

		return user;
	}

	// 해당 아이디의유저가 매긴 리뷰개수를 반환한다.
	public Long getUserReviewCounts(Long userNum) {
		return userDAO.findcountOrderByUserNum(userNum);
		// return getUserReviewCounts(userNum);
	}

	// 해당아이디가 작성한 리스트의 개수를 반환한다.
	public Long getUserListCounts(Long userNum) {
		return userDAO.findcountListByUserNum(userNum);
		// return getUserListCounts(userNum);
	}

	// 유저가 선호하는 음식종류 상위 3개를 반환한다.
	public List<String> getFoodTypeByReview(Long userNum) {
		return userDAO.findListByUserNumNFoodType(userNum);
	}

	// 특정 유저가 작성한 리뷰 역시간순으롤 조회 review
	public List<ReviewThumbnail> getAllOrderByUserNumNDate(Long userNum) {
		List<ReviewThumbnail> revDateThumbnailList = new ArrayList<>();
		revDAO.findAllOrderByUserNumNDate(userNum).forEach(rev -> revDateThumbnailList.add(ReviewThumbnail.of(rev)));
		return revDateThumbnailList;
	}

	// 특정 유저가 작성한 리뷰 별점높은 순으로 조회
	public List<ReviewThumbnail> getAllOrderByUserNumNStar(Long userNum) {
		List<ReviewThumbnail> revStarThumbnailList = new ArrayList<>();
		revDAO.findAllOrderByUserNumNStar(userNum).forEach(rev -> revStarThumbnailList.add(ReviewThumbnail.of(rev)));
		return revStarThumbnailList;
	}

}
