package com.example.hello.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static final String KEY = "secret_key";
    private static final long DURATION = 1000 * 60 * 60 * 24;

    public static String generateToken(Map<String, Object> claims) {
        Date expiryDate = new Date(System.currentTimeMillis() + DURATION);

        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(expiryDate)
                .sign(Algorithm.HMAC256(KEY));
    }

    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }
}
