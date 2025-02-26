package com.example.MyDemoApplication.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDto {

    private String username;
    private String token;

    public UserResponseDto(String username, String token) {
        this.username = username;
        this.token = token;
    }
}
