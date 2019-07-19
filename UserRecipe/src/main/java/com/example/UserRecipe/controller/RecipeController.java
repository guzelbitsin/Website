package com.example.UserRecipe.controller;

import com.example.UserRecipe.domain.*;
import com.example.UserRecipe.service.RecipeService;
import com.example.UserRecipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class RecipeController {
    private final RecipeService recipeService;
    private final UserService userService;
    @Autowired
    public RecipeController(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }
    @RequestMapping("/recipes/add")
    public ModelAndView recipeAddPage() {
        return new ModelAndView("addRecipe", "recipeForm", new RecipeAddForm());
    }

    @RequestMapping(value = "/recipes", method = RequestMethod.POST)
    public String handleRecipeAdd(
            @Valid @ModelAttribute("recipeForm")  RecipeAddForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "addRecipe";
        recipeService.addRecipe(form);
        return "redirect:/recipes";
    }
    @RequestMapping("/recipes")
    public ModelAndView getRecipesPage() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("recipes", recipeService.getAllRecipes());
        model.put("usernames", userService.getUsernames());
        model.put("assignForm", new RecipeAssignForm());
        return new ModelAndView("recipes", model);
    }

    @RequestMapping(value = "/recipes/{id}", method = RequestMethod.DELETE)
    public String handleRecipeDelete(@PathVariable Long id) {
        recipeService.deleteRecipeById(id);
        return "redirect:/recipes";
    }
    @RequestMapping(value = "/recipes/{id}", method = RequestMethod.GET)
    public ModelAndView getRecipesPage(@PathVariable Long id) {

        return new ModelAndView("recipePage" ,"recipes", recipeService.getRecipeById(id));
    }
    @RequestMapping(value = "/recipes/{id}", method = RequestMethod.PUT)
    public String handleRecipeAssign(@ModelAttribute("user") RecipeAssignForm form, @PathVariable("id") long id) {
        recipeService.assignRecipe(form.getUsername(), id);
        return "redirect:/recipes";
    }

    @RequestMapping(value = "/recipes/edit/{id}", method = RequestMethod.GET)
    public ModelAndView handleRecipeUpdate(@ModelAttribute("recipe") RecipeUpdateForm form, @PathVariable long id) {
        RecipeUpdateForm bufForm = new RecipeUpdateForm();
        Recipe rp = recipeService.getRecipeById(id);
        bufForm.setId(id);
        bufForm.setRecipeTag(rp.getTag());
        bufForm.setRecipeImage(rp.getImage());
        bufForm.setRecipeDesc(rp.getDescription());
        bufForm.setRecipeName(rp.getName());

        return new ModelAndView("updateRecipe", "recipeForm2", bufForm);
    }

    @RequestMapping(value = "/recipes/edit/{id}", method = RequestMethod.POST)
    public String handleRecipeUpdate(
            @Valid @ModelAttribute("recipeForm2")  RecipeUpdateForm form, BindingResult bindingResult,@PathVariable Long id) {
        if (bindingResult.hasErrors())
            return "updateRecipe";

        recipeService.updateRecipeByIdAndForm(form,id);
        return "redirect:/recipes";
    }

    @GetMapping("/recipes/search")
    public String getProduct(Model model,
                             @ModelAttribute("myFormObject") RecipeSearchForm myFormObject,
                             BindingResult result) {
        Iterable<Recipe> list = recipeService.findByNames(myFormObject.getSearchName());
        model.addAttribute("recipes", list);
        return "recipes";
    }


    //rest methods
    @RequestMapping(method = RequestMethod.GET, value = "/rest/recipes")
    @ResponseBody
    public ResponseEntity<Map<String,Set>> getRecipesRest() {
        Iterable<Recipe> owners = recipeService.getAllRecipes();
        Set<Recipe> newSet = new HashSet<>();
        for(Recipe e : owners)
            newSet.add(e);

        Map<String,Set> map = new HashMap<>();
        map.put("recipes",newSet);
        return ResponseEntity.ok(map);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/rest/recipes/add",consumes = "application/json")
    @ResponseBody
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
    @ResponseBody
    public ResponseEntity<Recipe> getRecipeRest(@PathVariable Long id) {
        Recipe rp = recipeService.getRecipeById(id);
        return ResponseEntity.ok(rp);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/rest/recipes/delete/{id}")
    @ResponseBody
    public ResponseEntity<Recipe> deleteRecipesRest(@PathVariable Long id) {
        Recipe rp = recipeService.getRecipeById(id);
        recipeService.deleteRecipeById(id);
        return ResponseEntity.ok(rp);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/rest/recipes/edit/{id}")
    @ResponseBody
    public ResponseEntity<Recipe> updateRecipesRest(@RequestBody(required = false) Recipe rp, @PathVariable Long id) {
        recipeService.updateRecipeById(rp,id);
        return ResponseEntity.ok(rp);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/rest/recipes/search/{name}")
    @ResponseBody
    public ResponseEntity<Iterable<Recipe>> searchRecipesRest(@PathVariable String name) {
        Iterable<Recipe> rp =recipeService.findByNames(name);
        return ResponseEntity.ok(rp);
    }

    @RequestMapping(value = "/rest/recipes/assign/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<User> handleRecipeAssign(@RequestBody User user, @PathVariable("id") long id) {
        recipeService.assignRecipe(user.getUsername(), id);
        return ResponseEntity.ok(user);
    }


}