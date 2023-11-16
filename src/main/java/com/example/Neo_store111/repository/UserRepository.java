package com.example.Neo_store111.repository;

import com.example.Neo_store111.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findAllByUserId(Long id);

    Optional<User> findUserByEmail(String email);
}
