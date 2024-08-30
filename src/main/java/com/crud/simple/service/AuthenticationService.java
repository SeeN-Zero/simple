package com.crud.simple.service;

import com.crud.simple.dto.request.LoginRequest;
import com.crud.simple.dto.request.RegisterRequest;
import com.crud.simple.dto.response.GenericResponse;
import com.crud.simple.dto.response.JwtResponse;
import com.crud.simple.entity.UserApp;
import com.crud.simple.repository.UserAppRepository;
import com.crud.simple.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserAppRepository userAppRepository;
    private final AuthenticationManager authenticationManager;

    public GenericResponse register(RegisterRequest request) {
        if (userAppRepository.findByEmailIgnoreCase(request.getEmail()).isPresent()) {
            return GenericResponse.builder().message("Email Sudah Terpakai").build();
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        userAppRepository.save(UserApp.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .name(request.getName())
                .phone(request.getPhone())
                .address(request.getAddress()).build());
        return GenericResponse.builder().message("Success").build();
    }

    public JwtResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userAppRepository.findByEmailIgnoreCase(request.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return JwtResponse.builder().token(jwt).build();
    }
}
