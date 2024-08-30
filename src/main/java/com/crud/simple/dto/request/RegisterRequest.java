package com.crud.simple.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email not valid")
    private String email;
    @NotBlank(message = "Password cannot be empty")
    private String password;
    @NotBlank(message = "name cannot be empty")
    private String name;
    @NotBlank(message = "Address cannot be empty")
    private String address;
    @NotBlank(message = "Phone cannot be empty")
    private String phone;


}
