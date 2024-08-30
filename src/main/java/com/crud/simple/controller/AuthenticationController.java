package com.crud.simple.controller;


import com.crud.simple.dto.request.LoginRequest;
import com.crud.simple.dto.request.RegisterRequest;
import com.crud.simple.dto.response.GenericResponse;
import com.crud.simple.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/user/add")
    public ResponseEntity<GenericResponse> signUp(@Valid @RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(authenticationService.register(request));
        } catch (Exception e) {
            log.error("Exception /user/add : {}", e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(GenericResponse.builder().message("Unknown Error").build());
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(authenticationService.login(request));
        }catch (BadCredentialsException e){
            log.error("Exception /user/login : {}", e.toString());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(GenericResponse.builder().message("Bad Credential").build());

        }
        catch (Exception e) {
            log.error("Exception /user/login : {}", e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(GenericResponse.builder().message("Unknown Error").build());
        }
    }
}
