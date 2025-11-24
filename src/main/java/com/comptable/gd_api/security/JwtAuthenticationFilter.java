package com.comptable.gd_api.security;

import com.comptable.gd_api.dto.response.AuthResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, String> credentials = objectMapper.readValue(request.getInputStream(), Map.class);
            String email = credentials.get("email");
            String password = credentials.get("password");

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse login request", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException {

        String username = authResult.getName();
        String token = jwtUtil.generateToken(username);

        AuthResponseDTO authResponse = AuthResponseDTO.builder()
                .message("User logged in successfully")
                .userEmail(username)
                .token(token)
                .build();

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(objectMapper.writeValueAsString(authResponse));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("{\"message\":\"Invalid credentials\"}");
    }
}
