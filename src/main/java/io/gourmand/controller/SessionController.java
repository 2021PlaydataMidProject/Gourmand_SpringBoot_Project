package io.gourmand.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.gourmand.domain.User;
import io.gourmand.dto.SessionResponseDTO;
import io.gourmand.service.UserService;
import io.gourmand.util.JwtUtil;

@RestController
public class SessionController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserService userService;
	
	// 로그인
	@PostMapping("/session")
	public ResponseEntity<SessionResponseDTO> create(@RequestBody User resource) throws URISyntaxException {
		

		String userId = resource.getUserId();
		String pw = resource.getPw();
		
		User user = userService.authenticate(userId, pw);
		
		String accessToken = jwtUtil.createToken(user.getUserNum(), user.getUserId(), user.getName()); 

		
		String url = "/session";
		return ResponseEntity.created(new URI(url)).body(
				SessionResponseDTO.builder()
				.accessToken(accessToken)
				.build());
	}
	
}