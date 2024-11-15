package com.polstat.ukmbulstik.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

@Component
public class JwtUtil implements Serializable {

    private SecretKey secretKey;

    @Value("${jwt.expiration}")
    private long expireDuration;

    // Inisialisasi kunci, periksa apakah panjangnya memadai
    public JwtUtil(@Value("${jwt.secret}") String secret) {
        if (Base64.getDecoder().decode(secret).length >= 32) {
            this.secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
        } else {
            System.out.println("Kunci dari konfigurasi tidak cukup panjang. Menghasilkan kunci baru yang aman.");
            this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        }
    }

    public String generateAccessToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuer("UKM Bulstik")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireDuration))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            System.out.println("JWT expired: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.out.println("Token is null, empty, or only whitespace: " + ex.getMessage());
        } catch (MalformedJwtException ex) {
            System.out.println("JWT is invalid: " + ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            System.out.println("JWT is not supported: " + ex.getMessage());
        } catch (SignatureException ex) {
            System.out.println("Signature validation failed: " + ex.getMessage());
        }
        return false;
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
