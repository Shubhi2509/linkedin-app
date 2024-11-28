package com.project.linkedin.user_service.controller;

import com.project.linkedin.user_service.dto.LoginRequestDto;
import com.project.linkedin.user_service.dto.SignUpRequestDto;
import com.project.linkedin.user_service.dto.UserDto;
import com.project.linkedin.user_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

private  final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) throws BadRequestException {
        UserDto userDto=authService.signUp(signUpRequestDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto) throws BadRequestException {
        String token=authService.login(loginRequestDto);
        return ResponseEntity.ok(token);
    }
}
