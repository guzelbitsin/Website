package com.website.spring.service;

import com.website.spring.dao.mem.RecipeRepository;
import com.website.spring.exception.RecipeNotFoundException;
import com.website.spring.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    @Autowired
    public void setRecipeRepository(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> findRecipes() {
        return null;
    }

    @Override
    public List<Recipe> findRecipeName(String name) {
        return null;
    }

    @Override
    public List<Recipe> findRecipeTag(String name) {
        return null;
    }

    @Override
    public Recipe findRecipe(int id) throws RecipeNotFoundException {
        return null;
    }

    @Override
    public void createRecipe(Recipe recipe) {

    }

    @Override
    public void update(Recipe recipe) {

    }

    @Override
    public void deleteRecipe(int id) {

    }


}
