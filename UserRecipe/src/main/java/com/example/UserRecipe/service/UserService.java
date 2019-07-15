package com.example.UserRecipe.service;

import com.example.UserRecipe.domain.Recipe;
import com.example.UserRecipe.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    void addUser(User user);
    Iterable<User> getUsers();
    List<String> getUsernames();
    User getUserByUsername(String username);
    User getUserById(long id);
    Map<String, List<Recipe>> numberOfRecipesByName(long userId);
}
