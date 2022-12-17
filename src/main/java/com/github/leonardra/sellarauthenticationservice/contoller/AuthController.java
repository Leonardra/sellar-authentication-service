package com.github.leonardra.sellarauthenticationservice.contoller;


import com.github.leonardra.sellarauthenticationservice.exceptions.UsernameAlreadyExistsException;
import com.github.leonardra.sellarauthenticationservice.model.LoginRequest;
import com.github.leonardra.sellarauthenticationservice.model.dtos.UserRequest;
import com.github.leonardra.sellarauthenticationservice.service.SellarUserService;
import com.github.leonardra.sellarauthenticationservice.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final SellarUserService sellarUserService;

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager, SellarUserService sellarUserService) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.sellarUserService = sellarUserService;
    }

    @PostMapping("/token")
    public String token(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        LOG.info("Token requested for user {} at {}", authentication.getName(), LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return tokenService.generateToken(authentication);
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest){
        try{
            return new ResponseEntity<>(sellarUserService.createUser(userRequest), HttpStatus.CREATED);
        }catch(UsernameAlreadyExistsException exception){
            LOG.info(exception.getMessage());
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
