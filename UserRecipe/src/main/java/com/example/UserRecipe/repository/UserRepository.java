package com.example.UserRecipe.repository;

import com.example.UserRecipe.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User getUserByUsername(String username);
}