package com.crud.simple.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDeleteRequest {
    private String email;
}
