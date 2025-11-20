package com.comptable.gd_api.service.impl;

import com.comptable.gd_api.dto.request.AuthRequestDTO;
import com.comptable.gd_api.dto.response.AuthResponseDTO;
import com.comptable.gd_api.security.JwtUtil;
import com.comptable.gd_api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO) throws AuthenticationException {
        String username = authRequestDTO.getEmail();
        String password = authRequestDTO.getPassword();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));


        String message = "User logged in successfully : " + username;
        String token = jwtUtil.generateToken(username);

        return AuthResponseDTO.builder().message(message).token(token).build();
    }


}
