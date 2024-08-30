package com.crud.simple.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email not valid")
    private String email;
    @NotBlank(message = "Password cannot be empty")
    private String password;
}
