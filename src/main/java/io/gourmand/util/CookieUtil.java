package io.gourmand.util;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class CookieUtil {

    public static Cookie createCookie(String cookieName, String value){
        Cookie token = new Cookie(cookieName,value);
        token.setHttpOnly(true);
        token.setMaxAge((int)JwtUtil.TOKEN_VALIDATION_SECOND);
        token.setPath("/");
        return token;
    }

    public Cookie getCookie(HttpServletRequest req, String cookieName){
        final Cookie[] cookies = req.getCookies();
        if(cookies==null) return null;
        for(Cookie cookie : cookies){
            if(cookie.getName().equals(cookieName))
                return cookie;
        }
        return null;
    }
    
    
    //특정 쿠키 제거
    @PostMapping("/auth/logout")
    public void deleteCookie(HttpServletResponse response) throws IOException{
	// cookieName="accessToken"
    Cookie cookie = new Cookie("accessToken", null); // choiceCookieName(쿠키 이름)에 대한 값을 null로 지정

    cookie.setMaxAge(0); // 유효시간을 0으로 설정
    System.out.println("test-----------------------!!");
    response.addCookie(cookie); // 응답 헤더에 추가해서 없어지도록 함
    
    //response.sendRedirect("test");

    }
    
    

}