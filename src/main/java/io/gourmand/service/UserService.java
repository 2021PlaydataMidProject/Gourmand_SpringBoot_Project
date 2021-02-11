package io.gourmand.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import io.gourmand.dao.ResImgRepository;
import io.gourmand.dao.UserImgRepository;
import io.gourmand.dao.UserRepository;
import io.gourmand.domain.Res;
import io.gourmand.domain.ResImg;
import io.gourmand.domain.User;
import io.gourmand.domain.UserImg;
import io.gourmand.dto.UserDTO.SigninRequest;
import io.gourmand.dto.UserDTO.UserRegister;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	UserImgRepository userImgDAO;

	/**
	 * 아이디과 비밀번호가 일치하는 유저를 조회한다. 해당 아이디 유저가 존재하지 않거나 비밀번호가 일치하지 않으면 Exception
	 * 
	 * @param 아이디
	 * @param pw  비밀번호
	 * @return 아이디 비밀번호가 일치하는 유저
	 */
	public User getMatchedUser(SigninRequest sign) {
		User signin = userRepository.findUserByUserId(sign.getUserId());
		// 없는 유저
		if (signin == null || !signin.getPw().equals(sign.getPw())) {
			return null;
		}
		return signin;
	}

// 회원 정보 조회
	public Optional<User> getUser(String userId) {
		return userRepository.findById(userId);
	}
	
//	회원가입
	public void insertUser(UserRegister user) {
		userRepository.save(UserRegister.toEntity(user));
	}
	
	//MultipartFile -> entity -> SQL저장
	public UserImg insertUserImg(MultipartFile userImg, User user){
		return userImgDAO.save(UserImg.of(userImg, user));
	}
	
	//MultipartFile -> 저장
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
	
//	회원 탈퇴
	public void deleteUser(User user) {
		userRepository.deleteById(user.getUserId());
	};

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
}
