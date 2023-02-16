package com.seanrw.commerce.config;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

@Component
public class JwtConfig {
    private final SignatureAlgorithm sigAlg = SignatureAlgorithm.HS256;
    private final Key signingKey;

    public JwtConfig(@Value("${jwt.salt}") String salt) {
        byte[] saltedBytes = Base64.getDecoder().decode(salt);
        signingKey = new SecretKeySpec(saltedBytes, sigAlg.getJcaName());
    }

    public int getExpiration() {
        return 60 * 60 * 1000; // 1h
    }

    public SignatureAlgorithm getSigAlg() {
        return sigAlg;
    }

    public Key getSigningKey() {
        return signingKey;
    }
}