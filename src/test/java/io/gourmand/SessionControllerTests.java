package io.gourmand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import io.gourmand.util.JwtUtil;

public class SessionControllerTests {

	@MockBean
	private JwtUtil jwtUtil;
	
	@Autowired
	private JwtUtil jwtUil;
	
//	given(jwtUtil.createToken(user_num, name))
//		.willReturn("header.payload.signature");
}
