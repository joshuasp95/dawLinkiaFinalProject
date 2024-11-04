package com.daw.finalProject.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.java.Log;

@Component
@Log
public class JwtUtil {

    private final SecretKey secretKey;

    // tiempo de expiracion del token
    private long jwtExpirationTimeInMs = 3600000;

    public JwtUtil() {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    /**
     * Genera un token JWT basado en el nombre de usuario.
     *
     * @param username Nombre de usuario (email) del usuario
     * @return Token JWT generado
     */
    public String generateToken(String mail) {

        return Jwts.builder()
                .setSubject(mail)
                // Fecha de emision del token
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationTimeInMs))
                .signWith(secretKey)
                .compact();
    }

    /**
     * Extrae los claims del token JWT.
     *
     * @param token Token JWT
     * @return claims de usuario extraído del token
     */
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                // clave secreta para validar el token
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    /**
     * Valida si el token JWT es válido.
     *
     * @param authToken Token JWT a validar
     * @param email     del usuari
     * @return True si el token es válido, false en caso contrario
     */
    public boolean validateToken(String token, String email) {
        final String username = extractUsername(token);
        return (username.equals(email) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}
