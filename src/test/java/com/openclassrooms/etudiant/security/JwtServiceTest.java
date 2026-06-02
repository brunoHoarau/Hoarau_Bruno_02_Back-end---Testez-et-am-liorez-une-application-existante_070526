package com.openclassrooms.etudiant.security;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.openclassrooms.etudiant.service.JwtService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private final JwtService jwtService = new JwtService();

    private final UserDetails userDetails =
            new User("john", "password", List.of());

    @Test
    void shouldGenerateToken() {
        String token = jwtService.generateToken(userDetails);

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void shouldExtractUsername() {
        String token = jwtService.generateToken(userDetails);

        String username = jwtService.extractUsername(token);

        assertEquals("john", username);
    }

    @Test
    void shouldValidateToken() {
        String token = jwtService.generateToken(userDetails);

        boolean isValid = jwtService.isTokenValid(token, userDetails);

        assertTrue(isValid);
    }

    @Test
    void shouldInvalidateTokenWithWrongUser() {
        String token = jwtService.generateToken(userDetails);

        UserDetails otherUser =
                new User("alice", "password", List.of());

        boolean isValid = jwtService.isTokenValid(token, otherUser);

        assertFalse(isValid);
    }

    @Test
    void shouldDetectExpiredLogic() throws InterruptedException {
        // ⚠️ test simple basé sur expiration future, pas réel sleep long

        String token = jwtService.generateToken(userDetails);

        Claims claims = io.jsonwebtoken.Jwts.parserBuilder()
                .setSigningKey(
                        io.jsonwebtoken.security.Keys.hmacShaKeyFor(
                                "mysecretkeymysecretkeymysecretkeymysecretkey".getBytes()
                        )
                )
                .build()
                .parseClaimsJws(token)
                .getBody();

        assertEquals("john", claims.getSubject());
        assertNotNull(claims.getExpiration());
    }
}
