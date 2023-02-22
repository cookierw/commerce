package com.seanrw.commerce.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

public class TokenServiceTests {
    
    private AutoCloseable closable;

    private final Instant mockNow = Instant.now();
    private final String mockScope = "read";
    private final String mockName = "testName";

    private Authentication mockAuthentication = Mockito.mock(Authentication.class);
    private JwtClaimsSet mockClaimsSet = Mockito.mock(JwtClaimsSet.class);
    
    @Mock
    private JwtEncoder mockEncoder;

    @InjectMocks
    private TokenService tokenService;

    @BeforeAll
    void setup() {
        closable = MockitoAnnotations.openMocks(this);

        mockClaimsSet = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(mockNow)
                .expiresAt(mockNow.plus(1, ChronoUnit.HOURS))
                .subject(mockName)
                .claim("scope", mockScope)
                .build();
    }

    @AfterAll
    void clean() throws Exception {
        closable.close();
    }

    @Test
    void generateTokenShouldReturnToken() {
        Mockito.when(mockEncoder.encode(Mockito.any(JwtEncoderParameters.class)).getTokenValue())
                    .thenReturn(JwtEncoderParameters.from(mockClaimsSet).toString());

    }
}
