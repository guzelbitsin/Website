package com.website.spring.dao;

import com.website.spring.model.Recipe;

import java.util.List;

public interface RecipeRepositoryInterface {
    List<Recipe> findAll();
    Recipe findById(int id);
    Recipe findByTag(String tag);
    Recipe findByName(String name);
    void create(Recipe recipe);
    Recipe update(Recipe recipe);
    void delete(String name);
}
