package com.openclassrooms.etudiant.service;


import java.security.Key;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET = "mysecretkeymysecretkeymysecretkeymysecretkey";

    /**
     * Génère clé signature pour signer et vérifier JWT
     *
     * @return clé HMAC pour signature tokens
     */
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    /**
     * Génère un token JWT pour un utilisateur authentifié.
     *
     * @param userDetails les informations de l'utilisateur
     * @return un token JWT signé avec expiration
     */
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extrait le nom d'utilisateur (subject) contenu dans un token JWT.
     *
     * @param token le token JWT
     * @return le username contenu dans le token
     */
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    /**
     * Vérifie si un token JWT est valide pour un utilisateur donné.
     *
     * @param token le token JWT à valider
     * @param userDetails les informations de l'utilisateur
     * @return true si le token est valide, sinon false
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * Vérifie si un token JWT est expiré.
     *
     * @param token le token JWT à analyser
     * @return true si le token est expiré, sinon false
     */
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    /**
     * Extrait l'ensemble des claims (payload) contenus dans un token JWT.
     *
     * @param token le token JWT
     * @return les claims extraits du token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

