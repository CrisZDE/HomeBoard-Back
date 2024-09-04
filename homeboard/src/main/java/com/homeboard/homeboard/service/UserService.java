package com.homeboard.homeboard.service;

import org.apache.el.stream.Optional;
import org.springframework.stereotype.Service;

import com.homeboard.homeboard.Repository.UserRepository;
import com.homeboard.homeboard.model.User;

@Service
public class UserService {
    
    private UserRepository userRepository;

    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    public User addNewUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(int userId){
        User user = userRepository.findById(userId).orElse(null);
        return user;
    }

    // public Optional<User> getUserByEmail(String email) {
    //     return userRepository.findByEmail(email);
    // }
}
