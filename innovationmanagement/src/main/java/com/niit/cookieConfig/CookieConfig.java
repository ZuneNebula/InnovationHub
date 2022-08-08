package com.niit.cookieConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;

@Component
public class CookieConfig {

//    @Value("${jwtSecretKey}")
//    private String jwtSecretKey;

    public String getInnovatorIdFromCookie(Cookie cookie){
        Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(cookie.getValue()).getBody();
        return (String) claims.get("innovatorId");
    }

    public String getExpertIdFromCookie(Cookie cookie){
        Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(cookie.getValue()).getBody();
        return (String) claims.get("expertId");
    }

    public String[] getExpertSpecializationFromCookie(Cookie cookie){
        Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(cookie.getValue()).getBody();
        return (String[]) claims.get("specialization");
    }
}
