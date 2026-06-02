package com.openclassrooms.etudiant.service;

import com.openclassrooms.etudiant.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import com.openclassrooms.etudiant.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    
    private static final Logger log =
            LoggerFactory.getLogger(UserService.class);

    public void register(User user) {
        Assert.notNull(user, "User must not be null");
        log.info("Registering new user");

        Optional<User> optionalUser = userRepository.findByLogin(user.getLogin());
        if (optionalUser.isPresent()) {
            throw new IllegalArgumentException("User with login " + user.getLogin() + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public String login(String login, String password) {
    	Assert.notNull(login, "Login must not be null"); 
    	Assert.notNull(password, "Password must not be null"); 
    	User user = userRepository.findByLogin(login) 
    			.orElseThrow(() -> new RuntimeException("Invalid credentials"));
    	
    	System.out.println("USER FOUND = " + user.getLogin());
    	
    	if (!passwordEncoder.matches(password, user.getPassword())) { 
    		throw new RuntimeException("Invalid credentials"); 
    	}
    	
    	UserDetails userDetails =
    	        org.springframework.security.core.userdetails.User
    	                .withUsername(user.getLogin())
    	                .password(user.getPassword())
    	                .authorities(List.of())
    	                .build();
    	
    	return jwtService.generateToken(userDetails);
    }

}
