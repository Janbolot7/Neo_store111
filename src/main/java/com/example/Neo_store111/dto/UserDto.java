package com.example.Neo_store111.dto;

import com.example.Neo_store111.model.Enum.Role;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class UserDto {
    private String email;
    private String password;
    private String firstName;;
    private String lastName;
    private int phoneNumber;
    private Role role;
}
