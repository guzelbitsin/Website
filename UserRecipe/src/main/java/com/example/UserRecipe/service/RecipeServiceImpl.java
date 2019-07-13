package com.example.UserRecipe.service;

import com.example.UserRecipe.domain.Recipe;
import com.example.UserRecipe.domain.RecipeAddForm;
import com.example.UserRecipe.domain.RecipeUpdateForm;
import com.example.UserRecipe.domain.User;
import com.example.UserRecipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService{
    private final RecipeRepository recipeRepository;
    private final UserService userService;
    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository,UserService userService) {
        this.recipeRepository = recipeRepository;
        this.userService = userService;
    }
    public void addRecipe(RecipeAddForm form) {
        Recipe recipe = new Recipe(form.getRecipeName(),form.getRecipeDesc(),form.getRecipeTag(),form.getRecipeImage());
        recipeRepository.save(recipe);
    }

    @Override
    public Iterable<Recipe> findByNames(String name) { return recipeRepository.findByName(name); }

    @Override
    public Iterable<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }
    @Override
    public void deleteRecipeById(long id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public void updateRecipeById(Recipe rp,long id) {
        Recipe recipe = recipeRepository.findById(id).get();
        recipe.setTag(rp.getTag());
        recipe.setImage(rp.getImage());
        recipe.setDescription(rp.getDescription());
        recipe.setName(rp.getName());
        recipeRepository.save(recipe);
    }

    @Override
    public void updateRecipeByIdAndForm(RecipeUpdateForm form, long id) {
        Recipe recipe = recipeRepository.findById(id).get();
        recipe.setTag(form.getRecipeTag());
        recipe.setImage(form.getRecipeImage());
        recipe.setDescription(form.getRecipeDesc());
        recipe.setName(form.getRecipeName());
        recipeRepository.save(recipe);
    }

    @Override
    public Recipe getRecipeById(long id) {
        return recipeRepository.findById(id).get();
    }

    @Override
    public Recipe assignRecipe(String username, long recipeId) {
        User user = userService.getUserByUsername(username);
        Recipe recipe = getRecipeById(recipeId);
        Set<Recipe> recipeList = user.getRecipes();
        recipeList.add(recipe);
        user.setRecipes(recipeList);
        recipe.setUser(user);
        return recipeRepository.save(recipe);
    }


}
