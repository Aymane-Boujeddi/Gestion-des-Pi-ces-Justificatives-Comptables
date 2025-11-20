package com.comptable.gd_api.service;

import com.comptable.gd_api.dto.request.AuthRequestDTO;
import com.comptable.gd_api.dto.response.AuthResponseDTO;
import org.springframework.security.core.AuthenticationException;

public interface AuthService {

    public AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO) throws AuthenticationException;

}
