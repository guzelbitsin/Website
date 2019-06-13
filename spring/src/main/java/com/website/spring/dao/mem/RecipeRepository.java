package com.website.spring.dao.mem;

import com.website.spring.dao.RecipeRepositoryInterface;
import com.website.spring.model.Recipe;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RecipeRepository implements RecipeRepositoryInterface {

    private Map<Integer,Recipe> recipeMap = new HashMap<>();

    public RecipeRepository(){

        Recipe recipe1 = new Recipe();
        recipe1.setId(0);
        recipe1.setName("ilkyemek");
        recipe1.setTag("iskender");
        recipe1.setDescription("yogurtlu iskender, tereyagli");

        Recipe recipe3 = new Recipe();
        recipe1.setId(1);
        recipe1.setName("ilkyemek");
        recipe1.setTag("iskender");
        recipe1.setDescription("yogurtlu iskender, tereyagli");

        Recipe recipe2 = new Recipe();
        recipe1.setId(2);
        recipe1.setName("ikinciyemek");
        recipe1.setTag("doner");
        recipe1.setDescription("tereyagli,et");

        recipeMap.put(recipe1.getId(),recipe1);
        recipeMap.put(recipe3.getId(),recipe3);
        recipeMap.put(recipe2.getId(),recipe2);
    }

    @Override
    public List<Recipe> findAll() {
        return new ArrayList<>(recipeMap.values());
    }

    @Override
    public Recipe findById(int id) {
        return recipeMap.get(id);
    }

    @Override
    public Recipe findByTag(String tag) {
        return recipeMap.get(tag);
    }

    @Override
    public Recipe findByName(String name) { return recipeMap.get(2);
    }

    @Override
    public void create(Recipe recipe) {
        recipe.setId(2123);
        recipeMap.put(recipe.getId(),recipe);
    }

    @Override
    public Recipe update(Recipe recipe) {
        recipeMap.replace(recipe.getId(),recipe);
        return recipe;
    }

    @Override
    public void delete(String name) {
        recipeMap.remove(name);
    }
}
