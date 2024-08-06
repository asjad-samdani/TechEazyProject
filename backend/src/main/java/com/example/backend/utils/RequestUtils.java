package com.example.backend.utils;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class RequestUtils {

    public static String GetTokenFromHeader(HttpServletRequest req) {
        String authHeader = req.getHeader("Authorization");
        return authHeader.substring(7);
    }
}
