package com.comptable.gd_api.controller;

import com.comptable.gd_api.dto.request.AuthRequestDTO;
import com.comptable.gd_api.dto.response.AuthResponseDTO;
import com.comptable.gd_api.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
//
//    @PostMapping("/login")
//    public ResponseEntity<AuthResponseDTO> loginUser(@Valid @RequestBody AuthRequestDTO authRequestDTO) {
//        AuthResponseDTO response = authService.authenticate(authRequestDTO);
//        return ResponseEntity.ok(response);
//    }
}
