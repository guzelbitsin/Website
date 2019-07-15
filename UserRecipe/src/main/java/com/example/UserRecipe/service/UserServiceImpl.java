package com.example.UserRecipe.service;

import com.example.UserRecipe.domain.Recipe;
import com.example.UserRecipe.domain.User;
import com.example.UserRecipe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService  {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<String> getUsernames() {
        List<String> usernames = new ArrayList<String>();
        Iterator iterator = getUsers().iterator();
        while (iterator.hasNext()) {
            User user = (User) iterator.next();
            usernames.add(user.getUsername());
        }
        return usernames;
    }
    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Map<String, List<Recipe>> numberOfRecipesByName(long userId) {
        Map<String, List<Recipe>> map = new HashMap<String, List<Recipe>>();
        Set<Recipe> recipes = getUserById(userId).getRecipes();
        for (Recipe recipe: recipes) {
            List<Recipe> recipeList = new ArrayList<Recipe>();
            String key = recipe.getName().toLowerCase();
            if (map.containsKey(key))
                recipeList = map.get(key);
            recipeList.add(recipe);
            map.put(key, recipeList);
        }
        return map;
    }
}
