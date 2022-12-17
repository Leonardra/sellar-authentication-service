package com.github.leonardra.sellarauthenticationservice.service;

import com.github.leonardra.sellarauthenticationservice.model.User;
import com.github.leonardra.sellarauthenticationservice.model.dtos.UserRequest;

public interface SellarUserService {
    User createUser(UserRequest userRequest);
}
