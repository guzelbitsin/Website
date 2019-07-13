package com.example.UserRecipe.repository;

import com.example.UserRecipe.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User getUserByUsername(String username);
}