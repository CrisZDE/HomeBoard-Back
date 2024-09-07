package com.homeboard.homeboard.controller;

import java.util.Optional;
import static com.homeboard.homeboard.config.ConstansSecurity.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.homeboard.homeboard.config.JWTAuthtenticationConfig;
import com.homeboard.homeboard.exception.HomeBoardException;
import com.homeboard.homeboard.model.User;
import com.homeboard.homeboard.service.UserService;


@RestController
public class UserController {
    
    JWTAuthtenticationConfig jwtAuthtenticationConfig = new JWTAuthtenticationConfig();

    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;

    }
    @PostMapping(LOGIN_URL)
    public User login(
        @RequestBody LoginRequest loginRequest) throws HomeBoardException {
            User user = userService.getUserByEmail(loginRequest.getEmail());
            String token = jwtAuthtenticationConfig.getJWTToken(user.getEmail());
            user.setToken(token);
            
            return user;
    }

    

    @PostMapping(SIGNIN_URL)
    public User signIn(@RequestBody User user) throws HomeBoardException{
        return userService.addNewUser(user);
    }
}
