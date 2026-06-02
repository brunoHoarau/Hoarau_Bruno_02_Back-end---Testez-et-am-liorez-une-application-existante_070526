package com.openclassrooms.etudiant.Entities;

import org.junit.jupiter.api.Test;

import com.openclassrooms.etudiant.entities.User;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldCreateUserWithAllArgsConstructor() {

        LocalDateTime now = LocalDateTime.now();

        User user = new User(
                1L,
                "John",
                "Doe",
                "login",
                "password",
                now,
                now
        );

        assertEquals(1L, user.getId());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("login", user.getLogin());
        assertEquals("password", user.getPassword());
        assertEquals(now, user.getCreated_at());
        assertEquals(now, user.getUpdated_at());
    }

    @Test
    void shouldSetAndGetFields() {

        User user = new User();

        user.setId(2L);
        user.setFirstName("Jane");
        user.setLastName("Smith");
        user.setLogin("admin");
        user.setPassword("secret");

        assertEquals(2L, user.getId());
        assertEquals("Jane", user.getFirstName());
        assertEquals("Smith", user.getLastName());
        assertEquals("admin", user.getLogin());
        assertEquals("secret", user.getPassword());
    }

    @Test
    void shouldReturnSecurityDefaults() {

        User user = new User();

        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
        assertTrue(user.isEnabled());

        assertNotNull(user.getAuthorities());
        assertTrue(user.getAuthorities().isEmpty());

        assertEquals("test", new User() {{
            setLogin("test");
        }}.getUsername());
    }
}