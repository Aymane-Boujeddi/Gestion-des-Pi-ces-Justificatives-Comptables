package com.comptable.gd_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRequestDTO {

    @NotNull(message = "Email is required")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password cannot be empty")
    private String password;
}
