package com.seanrw.commerce.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seanrw.commerce.config.JwtConfig;
import com.seanrw.commerce.dtos.responses.Principal;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {
    
    private JwtConfig jwtConfig;

    public JwtService(@Autowired JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String tokenize(Principal subject) {
        long now = System.currentTimeMillis();

        JwtBuilder builder = Jwts.builder()
                                .setId(subject.getId().toString())
                                .setIssuer("commerce")
                                .setIssuedAt(new Date(now))
                                .setExpiration(new Date(now + jwtConfig.getExpiration()))
                                .setSubject(subject.getUsername())
                                .claim("role", subject.getRole())
                                .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey());
        
        return builder.compact();
    }

    public Principal extractRequesterDetails(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSigningKey())
                    .parseClaimsJws(token)
                    .getBody();
            return new Principal(Long.parseLong(claims.getId()), claims.getSubject(), claims.get("role", String.class));
        } catch (Exception e) {
            return null;
        }
    }
}
