package com.raghu.ride.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;


@Service
public class AuthService {
    
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String generateToken(Long userId, int ttlDays) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                .withIssuer("ride")
                .withHeader(Map.of("alg", "HS256"))
                .withExpiresAt(new Date(System.currentTimeMillis() + ttlDays * 24 * 60 * 60 * 1000))
                .withClaim("userId", userId)
                .sign(algorithm);
        } catch (JWTCreationException exception){
            return null;
        }
    }

    public Long parseToken(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
                .withIssuer("ride")
                .build()
                .verify(token)
                .getClaim("userId")
                .asLong();
        }catch(Exception e){
            return null;
        }
    }

}
