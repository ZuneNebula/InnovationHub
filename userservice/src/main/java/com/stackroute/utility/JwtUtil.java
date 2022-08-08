package com.stackroute.utility;


import com.stackroute.expert.Expert;
import com.stackroute.innovator.Innovator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtUtil {
    static final long EXPIRATIONTIME = 3600000L;
    static String SIGNINGKEY;
    static final String PREFIX = "Bearer";

    public JwtUtil() {
    }

    @Value("${SIGNING_KEY}")
    public void setSigningkey(String signingkey) {
        SIGNINGKEY = signingkey;
    }

    public static String addExpertToken(HttpServletResponse res, Optional<Expert> expert) {
        Claims claims = Jwts.claims();
        System.out.println("SIGNINGKEY" + SIGNINGKEY);
        Expert exp = expert.get();
        claims.put("expertId", exp.getExpertId());
        claims.put("username", exp.getUsername());
        claims.put("firstName", exp.getFirstName());
        claims.put("lastName", exp.getLastName());
        claims.put("avatar", exp.getAvatarUrl());
        claims.put("email", exp.getEmail());
        claims.put("occupation", exp.getOccupation());
        claims.put("updatedBy", exp.getUpdatedBy());
        claims.put("updatedOn", exp.getUpdatedOn());
        claims.put("tags", exp.getTags());
        claims.put("specialization", exp.getSpecialization());
        claims.put("rating", exp.getRating());
        String jwtToken = Jwts.builder().setClaims(claims).setExpiration(new Date(System.currentTimeMillis() + 3600000L)).signWith(SignatureAlgorithm.HS512, SIGNINGKEY).compact();
        return jwtToken;
    }

    public static String addInnovatorToken(HttpServletResponse res, Optional<Innovator> innovator) {
        Claims claims = Jwts.claims();
        System.out.println("SIGNINGKEY" + SIGNINGKEY);
        Innovator inno = innovator.get();
        claims.put("innovatorId", inno.getInnovatorId());
        claims.put("username", inno.getUsername());
        claims.put("firstName", inno.getFirstName());
        claims.put("lastName", inno.getLastName());
        claims.put("avatar", inno.getAvatarUrl());
        claims.put("email", inno.getEmail());
        claims.put("occupation", inno.getOccupation());
        claims.put("updatedBy", inno.getUpdatedBy());
        claims.put("updatedOn", inno.getUpdatedOn());
        claims.put("tags", inno.getTags());
        String jwtToken = Jwts.builder().setClaims(claims).setExpiration(new Date(System.currentTimeMillis() + 3600000L)).signWith(SignatureAlgorithm.HS512, SIGNINGKEY).compact();
        return jwtToken;
    }
}

