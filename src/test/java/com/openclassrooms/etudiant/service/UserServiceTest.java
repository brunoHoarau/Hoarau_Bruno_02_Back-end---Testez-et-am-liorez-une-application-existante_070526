package com.openclassrooms.etudiant.service;

import com.openclassrooms.etudiant.entities.User;
import com.openclassrooms.etudiant.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    private static final String LOGIN = "LOGIN";
    private static final String PASSWORD = "PASSWORD";

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private UserService userService;

    // =========================
    // REGISTER (existing tests OK)
    // =========================

    // =========================
    // LOGIN - NULL LOGIN
    // =========================
    @Test
    void shouldThrowWhenLoginIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> userService.login(null, PASSWORD));
    }

    // =========================
    // LOGIN - NULL PASSWORD
    // =========================
    @Test
    void shouldThrowWhenPasswordIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> userService.login(LOGIN, null));
    }

    // =========================
    // LOGIN - USER NOT FOUND
    // =========================
    @Test
    void shouldThrowWhenUserNotFound() {

        when(userRepository.findByLogin(LOGIN))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> userService.login(LOGIN, PASSWORD));
    }

    // =========================
    // LOGIN - BAD PASSWORD
    // =========================
    @Test
    void shouldThrowWhenPasswordIsInvalid() {

        User user = new User();
        user.setLogin(LOGIN);
        user.setPassword("encoded");

        when(userRepository.findByLogin(LOGIN))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.matches(PASSWORD, "encoded"))
                .thenReturn(false);

        assertThrows(RuntimeException.class,
                () -> userService.login(LOGIN, PASSWORD));
    }

    // =========================
    // LOGIN - SUCCESS
    // =========================
    @Test
    void shouldReturnJwtTokenWhenLoginSuccess() {

        User user = new User();
        user.setLogin(LOGIN);
        user.setPassword("encoded");

        when(userRepository.findByLogin(LOGIN))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.matches(PASSWORD, "encoded"))
                .thenReturn(true);

        when(jwtService.generateToken(any()))
                .thenReturn("fake-jwt-token");

        String token = userService.login(LOGIN, PASSWORD);

        assertEquals("fake-jwt-token", token);

        verify(jwtService).generateToken(any());
    }
}