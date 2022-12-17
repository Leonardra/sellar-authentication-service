package com.github.leonardra.sellarauthenticationservice.service;

import com.github.leonardra.sellarauthenticationservice.model.SecurityUser;
import com.github.leonardra.sellarauthenticationservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class JpaUserDetailsService implements UserDetailsService {


    private static final Logger LOG = LoggerFactory.getLogger(JpaUserDetailsService.class);
    private final UserRepository userRepository;


    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(SecurityUser::new).orElseThrow(() -> new UsernameNotFoundException("User with "+username+" not found"));
    }


}
