package com.homeboard.homeboard.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeboard.homeboard.model.User;
import com.homeboard.homeboard.service.UserService;

@RestController
// @RequestMapping("/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;

    }

    @PostMapping("/add")
    public User addNewUser(@RequestBody User user){
        return userService.addNewUser(user);
    }
}
