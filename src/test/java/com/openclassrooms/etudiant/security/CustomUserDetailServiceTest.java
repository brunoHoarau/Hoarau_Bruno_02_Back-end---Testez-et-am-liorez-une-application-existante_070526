package com.openclassrooms.etudiant.security;

import com.openclassrooms.etudiant.configuration.security.CustomUserDetailService;
import com.openclassrooms.etudiant.entities.User;
import com.openclassrooms.etudiant.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailService customUserDetailService;

    // =========================
    // USER FOUND
    // =========================
    @Test
    void shouldLoadUserByUsername() {

        User user = new User();
        user.setLogin("john");
        user.setPassword("secret");

        given(userRepository.findByLogin("john"))
                .willReturn(Optional.of(user));

        UserDetails result = customUserDetailService.loadUserByUsername("john");

        assertNotNull(result);
        assertEquals("john", result.getUsername());
        assertEquals("secret", result.getPassword());
    }

    // =========================
    // USER NOT FOUND
    // =========================
    @Test
    void shouldThrowExceptionWhenUserNotFound() {

        given(userRepository.findByLogin("john"))
                .willReturn(Optional.empty());

        assertThrows(
                UsernameNotFoundException.class,
                () -> customUserDetailService.loadUserByUsername("john")
        );
    }
}