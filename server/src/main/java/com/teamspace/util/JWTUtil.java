package com.teamspace.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.node.TextNode;
import com.teamspace.exception.JWTTokenException;

import java.util.Date;

public class JWTUtil {

    private static final String SECRET = "secret";
    private static final String USER_ID = "userId";
    private static final String USER_NICKNAME = "userNickname";
    private static final Algorithm algorithmHS = Algorithm.HMAC256(SECRET);


    public static String createToken(Long userId, String userNickname) {
        return JWT.create()
                .withClaim(USER_ID, userId)
                .withClaim(USER_NICKNAME, userNickname)
                .withExpiresAt(new Date())
                .sign(algorithmHS);
    }

    public static Long getUserIdFromJwtToken(String token) {
        DecodedJWT decodedJWT = verifyToken(token);
        return decodedJWT.getClaims().get(USER_ID).asLong();
    }

    public static String getUserNicknameFromJwtToken(String token) {
        DecodedJWT decodedJWT = verifyToken(token);
        return decodedJWT.getClaims().get(USER_NICKNAME).as(TextNode.class).asText();
    }

    private static DecodedJWT verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithmHS)
                    .acceptExpiresAt(600)
                    .build();
            return verifier.verify(token);
        } catch (TokenExpiredException e) {
            throw new JWTTokenException("토큰 만료기한이 지났습니다. 다시 발급해주세요.");
        } catch (JWTDecodeException e) {
            throw new JWTTokenException("유효하지 않은 토큰입니다. 다시 발급해주세요.");
        }
    }
}
