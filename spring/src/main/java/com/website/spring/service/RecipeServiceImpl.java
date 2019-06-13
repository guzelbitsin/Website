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
        return recipeRepository.findAll();
    }

    @Override
    public Recipe findRecipeName(String name) { return recipeRepository.findByName(name);
    }

    @Override
    public List<Recipe> findRecipeTag(String name) {
        return (List<Recipe>) recipeRepository.findByTag(name);
    }

    @Override
    public Recipe findRecipeId(int id) throws RecipeNotFoundException {
        Recipe recipe = recipeRepository.findById(id);
        if(recipe == null) throw new RecipeNotFoundException("Recipe not found");
        return null;
    }

    @Override
    public void createRecipe(Recipe recipe) {
        recipeRepository.create(recipe);

    }

    @Override
    public void update(Recipe recipe) {
        recipeRepository.update(recipe);
    }

    @Override
    public void deleteRecipe(String name) { recipeRepository.delete(name);
    }


}
