package com.homeboard.homeboard.service;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.homeboard.homeboard.Repository.UserRepository;
import com.homeboard.homeboard.exception.HomeBoardException;
import com.homeboard.homeboard.model.User;

@Service
public class UserService {
    
    private UserRepository userRepository;

    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    public User addNewUser(User user) throws HomeBoardException{
            if (user == null) {
        throw new HomeBoardException("El usuario no puede ser nulo.", HttpStatus.BAD_REQUEST);
    }
        return userRepository.save(user);
    }

    public User getUserById(int userId) throws HomeBoardException {
        return userRepository.findById(userId).orElseThrow(() -> new HomeBoardException("Usuario no encontrado con id: " + userId, HttpStatus.NOT_FOUND));
    }

    public User getUserByEmail(String email) throws HomeBoardException {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new HomeBoardException("Usuario no encontrado con email: " + email, HttpStatus.NOT_FOUND));
    }
}
