package com.website.spring.service;

import com.website.spring.dao.RecipeRepository;
import com.website.spring.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServiceImpl{

    private RecipeRepository recipeRepository;

    @Autowired
    public void setRecipeRepository(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    public List<Recipe> findRecipes() { return recipeRepository.findAll();
    }

    public List<Recipe> findRecipeTag(String name) {
        List<Recipe> recipeList = new ArrayList<>();
        for(Recipe e : recipeRepository.findAll()){
            if(e.getTag().equalsIgnoreCase(name))
                recipeList.add(e);
        }
        return recipeList;
    }


    public void createRecipe(String name,String description,String image,String tag) {
        boolean flag=false;
        for(Recipe e:recipeRepository.findAll()){
            if(e.getName().equalsIgnoreCase(name))
                flag = true;
        }
        if(flag==false)
            recipeRepository.insert(new Recipe(name, description, tag, image));
        //recipeRepository.save(new Recipe(name, description, tag, image));
    }


    public void update(Recipe recipe) {
        Recipe rp = recipeRepository.findByName(recipe.getName());
        rp.setName(recipe.getName());
        rp.setTag(recipe.getTag());
        rp.setDescription(recipe.getDescription());
        rp.setImage(recipe.getImage());
        recipeRepository.save(rp);

    }

    public void deleteRecipe(String name) {
        for(Recipe rp : recipeRepository.findAll()){
            if(rp.getName().equalsIgnoreCase(name)){
                recipeRepository.delete(rp);
            }
        }

    }

    public Recipe findRecipeName(String name){
        for(Recipe rp : recipeRepository.findAll()){
            if(rp.getName().equalsIgnoreCase(name)){
                return rp;
            }
        }
        return null;
    }

}
