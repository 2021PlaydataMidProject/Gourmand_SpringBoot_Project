package io.gourmand.service;

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

//	회원가입
	public void insertUser(UserRegister user) {
		userRepository.save(UserRegister.toEntity(user));
	};

}
