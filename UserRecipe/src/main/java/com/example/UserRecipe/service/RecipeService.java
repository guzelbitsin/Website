package com.example.UserRecipe.service;

import com.example.UserRecipe.domain.Recipe;
import com.example.UserRecipe.domain.RecipeAddForm;
import com.example.UserRecipe.domain.RecipeUpdateForm;

public interface RecipeService {
    void addRecipe(RecipeAddForm form);
    Iterable<Recipe> getAllRecipes();
    void deleteRecipeById(long id);
    void updateRecipeById(Recipe rp,long id);
    void updateRecipeByIdAndForm(RecipeUpdateForm form,long id);
    Recipe getRecipeById(long id);
    Recipe assignRecipe(String username, long recipeId);
    Iterable<Recipe> findByNames(String name);
}
