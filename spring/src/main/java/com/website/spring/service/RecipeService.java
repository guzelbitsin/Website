package com.website.spring.service;

import com.website.spring.exception.RecipeNotFoundException;
import com.website.spring.model.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> findRecipes();
    List<Recipe> findRecipeName(String name);
    List<Recipe> findRecipeTag(String name);
    Recipe findRecipe(int id) throws RecipeNotFoundException;
    void createRecipe(Recipe recipe);
    void update(Recipe recipe);
    void deleteRecipe(int id);
}
