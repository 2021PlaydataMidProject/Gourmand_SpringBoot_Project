package io.gourmand.jwt;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.gourmand.dao.UserRepository;
import io.gourmand.util.JwtUtil;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity // 시큐리티 활성화 -> 기본 스프링 필터체인에 등록
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{	
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		Filter filter = new JwtAuthenticationFilter(
				authenticationManager(), jwtUtil());
		
		http
				.cors().disable()
				.csrf().disable()
				.formLogin().disable()
				.headers().frameOptions().disable()
				.and()
				.addFilter(filter)
				.sessionManagement() // 세션 관리하기 위함
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS); //세션에 대한 정책을 정함 -> 세션 관리하지 않음(STATELESS) -> 필터로 관리함
	}
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
		
	@Bean
	public JwtUtil jwtUtil() {
		return new JwtUtil(secret);
	}
}

//package io.gourmand.jwt;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import io.gourmand.jwt.JwtAuthenticationFilter;
//import io.gourmand.jwt.JwtAuthorizationFilter;
//
//import io.gourmand.dao.UserRepository;
//import lombok.RequiredArgsConstructor;
//
//@Configuration
//@EnableWebSecurity // 시큐리티 활성화 -> 기본 스프링 필터체인에 등록
//@RequiredArgsConstructor
//public class SecurityConfig extends WebSecurityConfigurerAdapter{	
//	
//	@Autowired
//	private UserRepository userRepository;
//	
//	@Autowired
//	private CorsConfig corsConfig;
//	
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//				.addFilter(corsConfig.corsFilter()) //@CrossOrigin(인증X), 시큐리티 필터에 등록 인증(인증O)
//				.csrf().disable()
//				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션 사용하지 않겠음
//			.and()
//				.formLogin().disable() //폼 로그인 쓰지 않음
//				.httpBasic().disable() //http방식 쓰지 않음
//				
//				.addFilter(new JwtAuthenticationFilter(authenticationManager()))
//				.addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
//				.authorizeRequests()
//				.antMatchers("/api/v1/user/**")
//				.access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
//				.antMatchers("/api/v1/admin/**")
//					.access("hasRole('ROLE_ADMIN')")
//				.anyRequest().permitAll();
//	}
//}
