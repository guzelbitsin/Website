package com.website.spring.web.controller;

import com.website.spring.exception.InternalServerException;
import com.website.spring.exception.RecipeNotFoundException;
import com.website.spring.model.Recipe;
import com.website.spring.service.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
public class RecipeRestController {

    @Autowired
    private RecipeServiceImpl recipeServiceImpl;

    @RequestMapping(method = RequestMethod.DELETE,value = "/deleteRecipe/{name}")
    public ResponseEntity<?> deleteRecipe(@PathVariable("name") String name){
        try {
            recipeServiceImpl.deleteRecipe(name);
            return ResponseEntity.ok().build();
        }catch(RecipeNotFoundException e){
            throw e;
        }catch (Exception ex){
            throw new InternalServerException(ex);
        }
    }


    @RequestMapping(method= RequestMethod.GET, value = "/findAllRecipes")
    public ResponseEntity<List<Recipe>> getRecipes(){
        List<Recipe> recipes = recipeServiceImpl.findRecipes();
        return ResponseEntity.ok(recipes);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/updateRecipe/{name}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable String name, @RequestBody Recipe recipeRequest){
        try{
            Recipe recipe = recipeServiceImpl.findRecipeName(name);
            recipe.setName(recipeRequest.getName());
            recipe.setTag(recipeRequest.getTag());
            recipe.setId(recipeRequest.getId());
            recipe.setDescription(recipeRequest.getDescription());

            recipeServiceImpl.update(recipe);
            return ResponseEntity.ok().build();
        }catch (RecipeNotFoundException ex){
            return ResponseEntity.notFound().build();
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    @RequestMapping(method = RequestMethod.POST,value = "/addRecipe")
    public ResponseEntity<URI> createRecipe(@RequestParam String name, @RequestParam String desc, @RequestParam String tag, @RequestParam String image ){
        try {
            recipeServiceImpl.createRecipe(name,desc,tag,image);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{name}").buildAndExpand(name).toUri();
            return ResponseEntity.created(location).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    //tag
    @RequestMapping(method=RequestMethod.GET,value = "/findRecipeTag/{tag}")
    public ResponseEntity<List<Recipe>> getRecipes(@PathVariable("tag") String name){
        List<Recipe> recipes = recipeServiceImpl.findRecipeTag(name);
        return ResponseEntity.ok(recipes);
    }


    @RequestMapping(method = RequestMethod.GET,value = "/findRecipe/{name}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable("name") String name)
    {
        try {
            Recipe recipe = recipeServiceImpl.findRecipeName(name);
            return ResponseEntity.ok(recipe);
        }catch (RecipeNotFoundException recipeExp){
            return ResponseEntity.notFound().build();
        }
    }
}
