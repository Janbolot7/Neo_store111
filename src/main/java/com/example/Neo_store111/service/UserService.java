package com.example.Neo_store111.service;

import com.example.Neo_store111.dto.UserInfo;
import com.example.Neo_store111.dto.request.UserRequest;
import com.example.Neo_store111.exception.NotFoundException;
import com.example.Neo_store111.model.Enum.Role;
import com.example.Neo_store111.model.User;
import com.example.Neo_store111.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User checkUserOnExistAndReturn(Long userId) {
        return userRepository.findAllByUserId(userId).orElseThrow( () -> new NotFoundException("User was not found"));
    }


    //ADMIN
    public ResponseEntity<Object> createUserAdmin(UserInfo userInfo, UserRequest userRequest) {
        ResultMod resultMod = new ResultMod();

        if (userRepository.findUserByEmail(userRequest.getEmail()).isPresent()) {
            resultMod.setResult("User with email:" + userRequest.getEmail() + " already exist!");
            return ResponseEntity.ok(resultMod.getResult());
        }

        userRepository.save(
                User.builder()
                        .email(userRequest.getEmail())
                        .password(userRequest.getPassword())
                        .userInfo(UserInfo.builder()
                                .firstName(userInfo.getFirstName())
                                .lastName(userInfo.getLastName())
                                .phoneNumber(userInfo.getPhoneNumber())
                                .build())
                        .role(Role.ADMIN)
                        .build());
        resultMod.setResult("User successfully established");
        return ResponseEntity.ok(resultMod.getResult());
    }


    //USER
    public ResponseEntity<Object> createUser(UserInfo userInfo , UserRequest userRequest) {
        ResultMod resultMod = new ResultMod();

        if (userRepository.findUserByEmail(userRequest.getEmail()).isPresent()) {
            resultMod.setResult("User with email:"  + userRequest.getEmail() +  " already exist!");
            return ResponseEntity.ok(resultMod.getResult());
        }

        userRepository.save(
                User.builder()
                        .email(userRequest.getEmail())
                        .password(userRequest.getPassword())
                        .userInfo(UserInfo.builder()
                                .firstName(userInfo.getFirstName())
                                .lastName(userInfo.getLastName())
                                .phoneNumber(userInfo.getPhoneNumber())
                                .build())
                        .role(Role.USER)
                        .build());
        resultMod.setResult("User successfully established");
        return ResponseEntity.ok(resultMod.getResult());
    }

    public UserInfo getUserById(Long id) {
        //add if, exception
        User user = checkUserOnExistAndReturn(id);
        return user.getUserInfo();
    }

    public ResponseEntity<Object> updateUserInfoById(Long id, UserInfo userInfo) {
        User user = checkUserOnExistAndReturn(id);
        ResultMod resultMod = new ResultMod();

        user.setUserInfo(userInfo);
        userRepository.save(user);
        resultMod.setResult("User info was updated");

        return ResponseEntity.ok(resultMod.getResult());
    }

    public ResponseEntity<Object> updateUserRequest(UserRequest userRequest, Long id) {
        User user = checkUserOnExistAndReturn(id);
        ResultMod resultMod = new ResultMod();

        user.setPassword(userRequest.getPassword());
        userRepository.save(user);
        resultMod.setResult("User password was updated!");

        return ResponseEntity.ok(resultMod.getResult());
    }

    public ResponseEntity<Object> deleteUserById(Long id){
        ResultMod resultMod = new ResultMod();
        if (userRepository.findById(id).isEmpty()) {
            resultMod.setResult("User not deleted, because user not found!");
            return ResponseEntity.ok(resultMod.getResult());
        }
        userRepository.deleteById(id);
        resultMod.setResult("User was deleted!");

        return ResponseEntity.ok(resultMod.getResult());
    }
}
