package com.website.spring.web.controller;

import com.website.spring.exception.InternalServerException;
import com.website.spring.exception.RecipeNotFoundException;
import com.website.spring.model.Recipe;
import com.website.spring.service.RecipeService;
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
    private RecipeService recipeService;

    @RequestMapping(method = RequestMethod.DELETE,value = "/deleteRecipe/{name}")
    public ResponseEntity<?> deleteRecipe(@PathVariable("name") String name){
        try {
            recipeService.deleteRecipe(name);
            return ResponseEntity.ok().build();
        }catch(RecipeNotFoundException e){
            throw e;
        }catch (Exception ex){
            throw new InternalServerException(ex);
        }
    }


    @RequestMapping(method= RequestMethod.GET, value = "/findAllRecipes")
    public ResponseEntity<List<Recipe>> getRecipes(){
        List<Recipe> recipes = recipeService.findRecipes();
        return ResponseEntity.ok(recipes);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/updateRecipe/{name}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable String name, @RequestBody Recipe recipeRequest){
        try{
            Recipe recipe = recipeService.findRecipeName(name);
            recipe.setName(recipeRequest.getName());
            recipe.setTag(recipeRequest.getTag());
            recipe.setId(recipeRequest.getId());
            recipe.setDescription(recipeRequest.getDescription());

            recipeService.update(recipe);
            return ResponseEntity.ok().build();
        }catch (RecipeNotFoundException ex){
            return ResponseEntity.notFound().build();
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    @RequestMapping(method = RequestMethod.POST,value = "/addRecipe")
    public ResponseEntity<URI> createRecipe(@RequestBody Recipe recipe){
        try {
            recipeService.createRecipe(recipe);
            String name = recipe.getName();
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{name}").buildAndExpand(name).toUri();
            return ResponseEntity.created(location).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    //tag
    @RequestMapping(method=RequestMethod.GET,value = "/getRecipeTag")
    public ResponseEntity<List<Recipe>> getRecipes(@RequestParam("tag") String name){
        List<Recipe> recipes = recipeService.findRecipeTag(name);
        return ResponseEntity.ok(recipes);
    }
    //name
    @RequestMapping(method = RequestMethod.GET,value = "/findAllRecipes/{name}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable("name") String name)
    {
        try {
            Recipe recipe = recipeService.findRecipeName(name);
            return ResponseEntity.ok(recipe);
        }catch (RecipeNotFoundException recipeExp){
            return ResponseEntity.notFound().build();
        }
    }
}
