package com.project.linkedin.user_service.service;

import com.project.linkedin.user_service.dto.LoginRequestDto;
import com.project.linkedin.user_service.dto.SignUpRequestDto;
import com.project.linkedin.user_service.dto.UserDto;
import com.project.linkedin.user_service.entity.User;
import com.project.linkedin.user_service.exception.ResourceNotFoundException;
import com.project.linkedin.user_service.repository.UserRepository;
import com.project.linkedin.user_service.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    public UserDto signUp(SignUpRequestDto signUpRequestDto) throws BadRequestException {
        boolean exists=userRepository.existsByEmail(signUpRequestDto.getEmail());
        if(exists){
            throw new BadRequestException("User already exists, cannot signup again");
        }
        User user=modelMapper.map(signUpRequestDto,User.class);
        user.setPassword(PasswordUtil.hashPasswor(signUpRequestDto.getPassword()));
        User savedUser=userRepository.save(user);
        return modelMapper.map(savedUser,UserDto.class);
    }

    public String login(LoginRequestDto loginRequestDto) throws BadRequestException {
        User user=userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(()-> new ResourceNotFoundException("User Not found with email: "+ loginRequestDto.getEmail()));
        boolean isPasswordMatch= PasswordUtil.checkPassword(loginRequestDto.getPassword(),user.getPassword());
        if(!isPasswordMatch){
            throw new BadRequestException("Incorrect password");
        }
        return jwtService.generateAccessToken(user);
    }
}
