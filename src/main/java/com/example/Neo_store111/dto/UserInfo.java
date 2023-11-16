package com.example.Neo_store111.dto;

import lombok.*;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String firstName;
    private String lastName;
    private int phoneNumber;
    //private Role role;
}
