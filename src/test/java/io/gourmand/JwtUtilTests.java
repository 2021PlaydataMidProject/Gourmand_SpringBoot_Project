package io.gourmand;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import io.gourmand.util.JwtUtil;
import io.jsonwebtoken.Claims;

public class JwtUtilTests {
	
	private static final String SECRET = "12345678901234567890123456789012";

	private JwtUtil jwtUtil;
	
	@Before
	public void setUp() {
		jwtUtil = new JwtUtil(SECRET);
	}
	
	@Test
	public void createToken() {
		
		String token = jwtUtil.createToken(2L, "test22","김식가");
		
		assertThat(token).contains(".");
	} 
	
	
	@Test
	public void getClaims() {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX251bSI6MiwidXNlcl9pZCI6InRlc3QyMiIsIm5hbWUiOiLquYDsi53qsIAifQ.5g2eLnPDjLMaz13YAa-LzT7x9LNkhqMz60IWlq8poXo";
		
		Claims claims = jwtUtil.getClaims(token);
		
		assertThat(claims.get("user_num", Long.class), is(2L));
		assertThat(claims.get("user_id"), is("test22"));
		assertThat(claims.get("name"), is("김식가"));
	}
}
