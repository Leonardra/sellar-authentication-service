package com.github.leonardra.sellarauthenticationservice.service;

import com.github.leonardra.sellarauthenticationservice.exceptions.UsernameAlreadyExistsException;
import com.github.leonardra.sellarauthenticationservice.model.User;
import com.github.leonardra.sellarauthenticationservice.model.dtos.UserRequest;
import com.github.leonardra.sellarauthenticationservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class SellarUserServiceImpl implements SellarUserService{

    private static final Logger LOG = LoggerFactory.getLogger(SellarUserServiceImpl.class);
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;



    public SellarUserServiceImpl(PasswordEncoder passwordEncoder, ModelMapper modelMapper, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserRequest userRequest) {
        if(userRepository.findByUsername(userRequest.getUsername()).isEmpty()){
            User user = modelMapper.map(userRequest, User.class);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setCreatedAt(LocalDateTime.now());
            user.setEnabled(true);
            return userRepository.save(user);
        }else{
            throw new UsernameAlreadyExistsException("User already exist: "+ userRequest.getUsername());
        }

    }
}
