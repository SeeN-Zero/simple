package com.crud.simple.service;

import com.crud.simple.dto.request.UserDeleteRequest;
import com.crud.simple.dto.request.UserUpdateRequest;
import com.crud.simple.dto.response.GenericResponse;
import com.crud.simple.entity.UserApp;
import com.crud.simple.repository.UserAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserAppRepository userAppRepository;

    public GenericResponse updateUser(UserUpdateRequest request) {
        UserApp userDetails = (UserApp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserApp userApp = userAppRepository.findByIdNotOptional(userDetails.getId());
        userApp.setName(request.getName());
        userApp.setAddress(request.getAddress());
        userApp.setPhone(request.getPhone());
        userAppRepository.save(userApp);
        return GenericResponse.builder().message("Success").build();
    }

    public GenericResponse deleteUser(UserDeleteRequest request) {
        userAppRepository.deleteByEmailIgnoreCase(request.getEmail());
        return GenericResponse.builder().message("Success").build();
    }
}
