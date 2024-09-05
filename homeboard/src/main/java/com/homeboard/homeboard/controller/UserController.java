package com.homeboard.homeboard.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.homeboard.homeboard.exception.HomeBoardException;
import com.homeboard.homeboard.model.User;
import com.homeboard.homeboard.service.UserService;

@RestController
public class UserController {

    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;

    }

    @PostMapping("/add")
    public User addNewUser(@RequestBody User user) throws HomeBoardException{
        return userService.addNewUser(user);
    }
}
