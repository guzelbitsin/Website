package com.example.UserRecipe.controller;

import com.example.UserRecipe.domain.User;
import com.example.UserRecipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/rest/user/{id}",method = RequestMethod.GET)
    public ResponseEntity<User> getUserRest(@PathVariable Long id) {
        User usr = userService.getUserById(id);
        return ResponseEntity.ok(usr);
    }

    @RequestMapping(value = "/rest/users",method = RequestMethod.GET)
    public ResponseEntity<Iterable<User>> getUsersRest() {
        Iterable<User> userList = userService.getUsers();
        return ResponseEntity.ok(userList);
    }

    @RequestMapping(value = "/rest/users/register",method = RequestMethod.POST)
    public ResponseEntity<User> addUsersRest(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok(user);
    }

}
