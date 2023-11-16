package com.example.Neo_store111.controller;

import com.example.Neo_store111.dto.UserInfo;
import com.example.Neo_store111.dto.request.UserRequest;
import com.example.Neo_store111.model.User;
import com.example.Neo_store111.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/to/admin")
public class AdminController {
    // Отсюда начинается контроллеры для ADMIN
    private final UserService service;
    @Autowired
    public AdminController(UserService service) {
        this.service = service;
    }

    @GetMapping("/getAllUsers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getAll() {
        return service.findAll();
    }

    @PostMapping("/createUserAdmin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> addUserByAdmin(UserRequest userRequest, UserInfo userInfo) {
        return service.createUserAdmin(userInfo, userRequest);
    }

    @GetMapping("/findUserBy/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserInfo getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> deleteUserById(@PathVariable Long id) {
        return service.deleteUserById(id);
    }
    //Здесь заканчиваются контроллеры для ADMIN
}
