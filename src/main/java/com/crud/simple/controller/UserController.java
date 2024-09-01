package com.crud.simple.controller;

import com.crud.simple.dto.request.UserDeleteRequest;
import com.crud.simple.dto.request.UserUpdateRequest;
import com.crud.simple.dto.response.GenericResponse;
import com.crud.simple.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Log4j2
public class UserController {

    private final UserService userService;

    @PostMapping("/user/update")
    public ResponseEntity<GenericResponse> updateUser(@RequestBody UserUpdateRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(request));
        } catch (Exception e) {
            log.error("Exception /individual/update : {}", e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(GenericResponse.builder().message("Unknown Error").build());
        }
    }

    @PostMapping("/user/delete")
    public ResponseEntity<GenericResponse> deleteUser(@RequestBody UserDeleteRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(request));
        } catch (Exception e) {
            log.error("Exception /user/delete : {}", e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(GenericResponse.builder().message("Unknown Error").build());
        }
    }


}
