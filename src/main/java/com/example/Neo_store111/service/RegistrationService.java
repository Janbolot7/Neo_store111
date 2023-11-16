package com.example.Neo_store111.service;

import com.example.Neo_store111.dto.request.UserRequest;
import com.example.Neo_store111.model.User;
import com.example.Neo_store111.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public String registration(UserRequest regisRequest) {
        ResultMod resultMod = new ResultMod();
        regisRequest.setPassword(passwordEncoder.encode(regisRequest.getPassword()));
        resultMod.setResult("User was registered in data base!");
        return resultMod.getResult();
    }

    public User parseUserRequest(UserRequest userRequest) {
        return User.builder()
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
    }
}
