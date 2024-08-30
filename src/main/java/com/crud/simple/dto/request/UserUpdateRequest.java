package com.crud.simple.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserUpdateRequest {
    private String name;
    private String address;
    private String phone;
}
