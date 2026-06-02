package com.openclassrooms.etudiant.configuration.security;

import com.openclassrooms.etudiant.repository.UserRepository;
import com.openclassrooms.etudiant.entities.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String login) {

        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getLogin())
                .password(user.getPassword())
                .authorities(List.of())
                .build();
    }

}