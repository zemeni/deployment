package com.example.testdevops.service;

import com.example.testdevops.payload.JWTAuthResponse;
import com.example.testdevops.payload.LoginDto;
import com.example.testdevops.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
