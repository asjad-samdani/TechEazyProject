package com.example.backend.utils;

import java.io.IOException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.backend.dto.ResponseDTO;
import com.example.backend.service.impl.AuthServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthUtils extends OncePerRequestFilter {

    private AuthServiceImpl authService;

    public AuthUtils(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        response.setContentType("application/json");
        String authHeader = request.getHeader("Authorization");
        ResponseDTO resp = new ResponseDTO();

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.setMessage("Unauthorized");

            String respStr = new ObjectMapper().writeValueAsString(resp);
            response.getWriter().write(respStr);
            return;
        }

        // Extract the token from the header
        String token = authHeader.substring(7);
        // Validate the token (this is just a placeholder, implement your logic)
        if (!validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.setMessage("Invalid token");
            String respStr = new ObjectMapper().writeValueAsString(resp);
            response.getWriter().write(respStr);
            return;
        }

        // Proceed with the request
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) throws ServletException {
        if (request.getRequestURI().contains("auth")) {
            return true;
        }
        return false;
    }

    private boolean validateToken(String token) {
        return authService.ValidateToken(token);
    }
}