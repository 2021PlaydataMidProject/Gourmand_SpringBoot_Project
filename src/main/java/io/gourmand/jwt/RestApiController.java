package io.gourmand.jwt;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.gourmand.dao.UserRepository;
import io.gourmand.domain.User;
import io.gourmand.jwt.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
// @CrossOrigin  // CORS 허용 
public class RestApiController {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// 모든 사람이 접근 가능
	@GetMapping("home")
	public String home() {
		return "<h1>home</h1>";
	}
	
	// Tip : JWT를 사용하면 UserDetailsService를 호출하지 않기 때문에 @AuthenticationPrincipal 사용 불가능.
	// 왜냐하면 @AuthenticationPrincipal은 UserDetailsService에서 리턴될 때 만들어지기 때문이다.
	
	// 유저 혹은 매니저 혹은 어드민이 접근 가능
	@GetMapping("user")
	public String user(Authentication authentication) {
		PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
		System.out.println("principal : "+principal.getUser().getUserId());
		System.out.println("principal : "+principal.getUser().getName());
		System.out.println("principal : "+principal.getUser().getPw());
		
		return "<h1>user</h1>";
	}
	
	// 매니저 혹은 어드민이 접근 가능
	@GetMapping("manager/reports")
	public String reports() {
		return "<h1>reports</h1>";
	}
		
		
	// 어드민이 접근 가능
	@GetMapping("admin/users")
	public List<User> users(){
		return userRepository.findAll();
	}
	
	@PostMapping("join")
	public String join(@RequestBody User user) {
		user.setPw(bCryptPasswordEncoder.encode(user.getPw()));
		user.setRoles("ROLE_USER");
		userRepository.save(user);
		return "회원가입완료";
	}
	
	// user, manager, admin 권한만 가능
	@GetMapping("/api/vi/user")
	public String user() {
		return "user";
	}
	
	// manager, admin 권한만 접근 가능
	@GetMapping("/api/vi/manager")
	public String manager() {
		return "manager";
	}
	
	// admin 권한만 가능
	@GetMapping("/api/vi/admin")
	public String admin() {
		return "admin";
	}
	
}