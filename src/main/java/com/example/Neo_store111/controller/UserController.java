package com.example.Neo_store111.controller;

import com.example.Neo_store111.dto.UserInfo;
import com.example.Neo_store111.dto.request.UserRequest;
import com.example.Neo_store111.model.User;
import com.example.Neo_store111.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserService service;

    // Отсюда у меня начинается контроллеры для USER
    @PostMapping("/createUser")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Object> addUser(UserRequest userRequest, UserInfo userInfo) {
        return service.createUser(userInfo, userRequest);
    }

    @PutMapping("/updateUserRequest/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Object> updateUserRequestById(@PathVariable Long id,
                                                        UserRequest userRequest) {
        return service.updateUserRequest(userRequest, id);
    }
    @PutMapping("/updateUserInfo/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Object> updateUserInfo(@PathVariable Long id,UserInfo userInfo) {
        return service.updateUserInfoById(id, userInfo);
    }
    // Здесь заканчиваются контроллеры для USER

    // -----------------------------------------
}
