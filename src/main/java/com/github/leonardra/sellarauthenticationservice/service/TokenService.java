package com.github.leonardra.sellarauthenticationservice.service;


import org.springframework.security.core.Authentication;

public interface TokenService {
    String generateToken(Authentication authentication);
}
