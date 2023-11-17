package com.example.Neo_store111.model;

import com.example.Neo_store111.dto.UserInfo;
import com.example.Neo_store111.model.Enum.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.Name;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Name("user_id")
    private Long userId;

    @Column(unique = true)
    private String password;
    @Email
    @Column
    private String email;

    @Embedded
    private UserInfo userInfo;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Order> orders;
}
