package com.example.UserRecipe.controller;

import com.example.UserRecipe.domain.User;
import com.example.UserRecipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/register")
    public ModelAndView getRegisterPage() {
        return new ModelAndView("register", "user", new User());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String handleRegisterForm(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "register";
        userService.addUser(user);
        return "redirect:/";
    }

    @RequestMapping("/users")
    public ModelAndView getUsersPage() {
        return new ModelAndView("users", "users", userService.getUsers());
    }

    @RequestMapping(value = "/users/{id}/recipes")
    public ModelAndView getUserPage(@PathVariable Long id) {
        if (null == userService.getUserById(id))
            throw new NoSuchElementException("User with id:" + id + " not found");
        else
            return new ModelAndView("userItems" ,"recipes", userService.numberOfRecipesByName(id));
    }

    @RequestMapping(value = "/rest/user/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getUserRest(@PathVariable Long id) {
        User usr = userService.getUserById(id);
        return ResponseEntity.ok(usr);
    }

    @RequestMapping(value = "/rest/users",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Iterable<User>> getUsersRest(@PathVariable Long id) {
        Iterable<User> userList = userService.getUsers();
        return ResponseEntity.ok(userList);
    }

}