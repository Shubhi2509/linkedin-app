package com.project.linkedin.user_service.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private  String email;
    private  String password;

}