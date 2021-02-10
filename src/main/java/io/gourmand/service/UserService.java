package io.gourmand.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.gourmand.dao.UserRepository;
import io.gourmand.domain.User;
import io.gourmand.dto.UserDTO.SigninRequest;
import io.gourmand.dto.UserDTO.UserRegister;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

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
	};
	
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
