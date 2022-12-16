package com.github.leonardra.sellarauthenticationservice.contoller;


import com.github.leonardra.sellarauthenticationservice.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class AuthController {
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public String token(Authentication authentication){
        LOG.info("Token requested for user {} at {}", authentication.getName(), LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        String token = tokenService.generateToken(authentication);
        LOG.info("Token granted: {}", token);
        return token;
    }
}
