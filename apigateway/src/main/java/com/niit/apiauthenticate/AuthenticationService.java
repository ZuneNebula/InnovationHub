package com.niit.apiauthenticate;


import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class AuthenticationService {
    @Autowired
    JWTSecurityTokenUtil jwtSecurityTokenUtil;

    public Authentication getAuthentication(ServerWebExchange serverWebExchange) {
        boolean valid = false;
        try {
            HttpCookie cookie = serverWebExchange.getRequest()
                    .getCookies().getFirst("JWT-TOKEN");
            if (cookie == null) {
                System.out.println("Invalid Token recieved");
            } else {
                System.out.println("Token recieved is++" + cookie.getValue());
                valid = jwtSecurityTokenUtil.validateToken(cookie.getValue());
                System.out.println("valid" + valid);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Unable to get JWT Token");
        } catch (ExpiredJwtException e) {
            System.out.println("JWT Token has expired");
        } catch (Exception e) {
            System.out.println("Internal error" + e.getMessage());
            e.printStackTrace();
        }
        if (valid) {
            return new UsernamePasswordAuthenticationToken(
                    valid, null, null);
        }
        return null;
    }
}