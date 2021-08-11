package com.teamspace.config;

import com.teamspace.exception.JWTTokenException;
import com.teamspace.util.JWTUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String token = getJwtToken(request);
        Long userId = JWTUtil.getUserIdFromJwtToken(token);
        request.setAttribute("userId", userId);
        return true;

    }

    private String getJwtToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null) {
            throw new JWTTokenException("토큰이 없습니다.");
        }

        if (!authorizationHeader.startsWith("Bearer ")) {
            throw new JWTTokenException("토큰 타입이 이상합니다.");
        }

        return authorizationHeader.substring(7);
    }
}
