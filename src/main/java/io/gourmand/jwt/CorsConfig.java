package io.gourmand.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

   @Bean
   public CorsFilter corsFilter() {
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      CorsConfiguration config = new CorsConfiguration();
      config.setAllowCredentials(true); // 내서버가 응답을 할 때 json을 자바스크립트에서 처리할 수 있게 할지를 설정하는 것
      config.addAllowedOrigin("*"); // 모든 ip에 응답을 허용하겠다 e.g. http://domain1.com
      config.addAllowedHeader("*"); // 모든 header에 응답을 허용하겠다
      config.addAllowedMethod("*"); // 모든 post, get, put, delete, patch 요청을 허용하겠다. 

      source.registerCorsConfiguration("/auth/**", config);
      return new CorsFilter(source);
   }
   //토큰: xxxx.. 만들어줘야함. id, pw 정상적으로 들어와서 로그인이 완료되면 토큰을 만들어주고 그걸 응답을 해준다.
   //요청할 때마다 header에 Authorization에 value값으로 토큰을 가지고옴
   //그때 토큰이 넘어오면 이 토큰이 내가 만든 토큰이 맞는지만 검증만 하면 됨. (RSA, HS256)
}