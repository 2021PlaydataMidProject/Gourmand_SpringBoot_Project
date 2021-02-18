package io.gourmand.jwt.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.gourmand.dao.UserRepository;
import io.gourmand.domain.User;
import lombok.RequiredArgsConstructor;

//http://localhost:8080/login => 여기서 동작을 안함 
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("PrincipalDetailsService : 진입");
		User user = userRepository.findByName(username);

		// session.setAttribute("loginUser", user);
		return new PrincipalDetails(user);
	}
}
