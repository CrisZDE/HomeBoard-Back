package com.homeboard.homeboard.service;


import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.homeboard.homeboard.Repository.UserRepository;
import com.homeboard.homeboard.config.JWTAuthtenticationConfig;
import com.homeboard.homeboard.controller.LoginRequest;
import com.homeboard.homeboard.exception.HomeBoardException;
import com.homeboard.homeboard.model.User;

@Service
public class UserService {
    
    private UserRepository userRepository;
    private JWTAuthtenticationConfig jwtAuthtenticationConfig;
    private PasswordEncoder passwordEncoder;
    

    public UserService (UserRepository userRepository, JWTAuthtenticationConfig jwtAuthtenticationConfig, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.jwtAuthtenticationConfig = jwtAuthtenticationConfig;
        this.passwordEncoder = passwordEncoder;

    }
    
    public User addNewUser(User user) throws HomeBoardException{

    if (user == null) {
        throw new HomeBoardException("El usuario no puede ser nulo.", HttpStatus.BAD_REQUEST);
    }
    if (userRepository.existsByEmail(user.getEmail())) {
        throw new HomeBoardException("No se registró, porque el email ya está siendo utilizado.",
                HttpStatus.CONFLICT);
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

    

    public String authenticateUser(LoginRequest loginRequest) throws HomeBoardException {
        User user = getUserByEmail(loginRequest.getEmail()); // Buscar el usuario

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new HomeBoardException("Contraseña incorrecta", HttpStatus.UNAUTHORIZED);
        }

        // Generar el JWT token
        return jwtAuthtenticationConfig.getJWTToken(user.getEmail());
    }
}
