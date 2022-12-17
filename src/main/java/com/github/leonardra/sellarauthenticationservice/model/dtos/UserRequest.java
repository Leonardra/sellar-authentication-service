package com.github.leonardra.sellarauthenticationservice.model.dtos;


import lombok.Data;
import lombok.NonNull;

@Data
public class UserRequest {
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String roles;
}
