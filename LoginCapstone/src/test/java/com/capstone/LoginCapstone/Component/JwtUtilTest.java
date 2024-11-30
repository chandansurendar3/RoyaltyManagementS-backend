package com.capstone.LoginCapstone.Component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil.setSecret("dqCBup2Rt9E2J9plUsKCr6YZwVKoEDANCtloEqMcpok=");
        jwtUtil.setExpiration(36000000); // 1 hour in milliseconds
    }

    @Test
    void testGenerateTokenAndValidateToken() {
        String username = "testuser";
        String token = jwtUtil.generateToken(username);

        assertNotNull(token);
        assertTrue(jwtUtil.validateToken(token, username));
    }

    @Test
    void testExtractUsername() {
        String username = "testuser";
        String token = jwtUtil.generateToken(username);

        assertEquals(username, jwtUtil.extractUsername(token));
    }

    @Test
    void testExtractExpiration() {
        String username = "testuser";
        String token = jwtUtil.generateToken(username);
        Date expirationDate = jwtUtil.extractExpiration(token);

        assertNotNull(expirationDate);
        assertFalse(expirationDate.before(new Date()));
    }

    @Test
    void testValidateTokenExpired() {
        String username = "testuser";
        JwtUtil shortLivedJwtUtil = new JwtUtil();
        shortLivedJwtUtil.setSecret("dqCBup2Rt9E2J9plUsKCr6YZwVKoEDANCtloEqMcpok=");
        shortLivedJwtUtil.setExpiration(731); // 1 millisecond
        String token = shortLivedJwtUtil.generateToken(username);

        assertFalse(!jwtUtil.validateToken(token, username));
    }

    @Test
    void testValidateTokenInvalidUsername() {
        String username1 = "testuser1";
        String token = jwtUtil.generateToken(username1);

        String username2 = "testuser2";
        assertFalse(jwtUtil.validateToken(token, username2));
    }
}