package com.example.UserRecipe.controller;

import com.example.UserRecipe.domain.Recipe;
import com.example.UserRecipe.domain.RecipeAddForm;
import com.example.UserRecipe.domain.User;
import com.example.UserRecipe.service.RecipeService;
import com.example.UserRecipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
public class RecipeRestController {

    private final RecipeService recipeService;
    private final UserService userService;
    @Autowired
    public RecipeRestController(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rest/recipes")
    public ResponseEntity<Map<String, Set>> getRecipesRest() {
        Iterable<Recipe> owners = recipeService.getAllRecipes();
        Set<Recipe> newSet = new HashSet<>();
        for(Recipe e : owners)
            newSet.add(e);

        Map<String,Set> map = new HashMap<>();
        map.put("recipes",newSet);
        return ResponseEntity.ok(map);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/rest/recipes/add",consumes = "application/json")
    public ResponseEntity<Recipe> addRecipesRest(@RequestBody(required = false) Recipe rp) {
        RecipeAddForm bufForm = new RecipeAddForm();
        bufForm.setRecipeTag(rp.getTag());
        bufForm.setRecipeImage(rp.getImage());
        bufForm.setRecipeDesc(rp.getDescription());
        bufForm.setRecipeName(rp.getName());
        recipeService.addRecipe(bufForm);
        return ResponseEntity.ok(rp);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/rest/recipes/{id}")
    public ResponseEntity<Recipe> getRecipeRest(@PathVariable Long id) {
        Recipe rp = recipeService.getRecipeById(id);
        return ResponseEntity.ok(rp);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/rest/recipes/delete/{id}")
    public ResponseEntity<Recipe> deleteRecipesRest(@PathVariable Long id) {
        Recipe rp = recipeService.getRecipeById(id);
        recipeService.deleteRecipeById(id);
        return ResponseEntity.ok(rp);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/rest/recipes/edit/{id}")
    public ResponseEntity<Recipe> updateRecipesRest(@RequestBody(required = false) Recipe rp, @PathVariable Long id) {
        recipeService.updateRecipeById(rp,id);
        return ResponseEntity.ok(rp);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/rest/recipes/search/{name}")
    public ResponseEntity<Iterable<Recipe>> searchRecipesRest(@PathVariable String name) {
        Iterable<Recipe> rp =recipeService.findByNames(name);
        return ResponseEntity.ok(rp);
    }

    @RequestMapping(value = "/rest/recipes/assign/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> handleRecipeAssign(@RequestBody User user, @PathVariable("id") long id) {
        recipeService.assignRecipe(user.getUsername(), id);
        return ResponseEntity.ok(user);
    }


}
