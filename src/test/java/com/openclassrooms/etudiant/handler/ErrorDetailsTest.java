package com.openclassrooms.etudiant.handler;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ErrorDetailsTest {

    @Test
    void shouldCreateWithNoArgsConstructor() {

        ErrorDetails error = new ErrorDetails();

        assertNull(error.getTimestamp());
        assertNull(error.getMessage());
        assertNull(error.getDetails());
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {

        LocalDateTime now = LocalDateTime.of(2025, 1, 1, 12, 0);

        ErrorDetails error = new ErrorDetails(
                now,
                "error message",
                "error details"
        );

        assertEquals(now, error.getTimestamp());
        assertEquals("error message", error.getMessage());
        assertEquals("error details", error.getDetails());
    }

    @Test
    void shouldAllowSettersAndGetters() {

        LocalDateTime now = LocalDateTime.now();

        ErrorDetails error = new ErrorDetails();

        error.setTimestamp(now);
        error.setMessage("something went wrong");
        error.setDetails("stacktrace or details");

        assertEquals(now, error.getTimestamp());
        assertEquals("something went wrong", error.getMessage());
        assertEquals("stacktrace or details", error.getDetails());
    }

    @Test
    void shouldSupportEqualsAndHashCode() {

        LocalDateTime now = LocalDateTime.of(2025, 1, 1, 12, 0);

        ErrorDetails e1 = new ErrorDetails(now, "msg", "details");
        ErrorDetails e2 = new ErrorDetails(now, "msg", "details");

        assertEquals(e1, e2);
        assertEquals(e1.hashCode(), e2.hashCode());
    }

    @Test
    void shouldHaveToStringContainingFields() {

        ErrorDetails error = new ErrorDetails(
                LocalDateTime.of(2025, 1, 1, 12, 0),
                "error message",
                "error details"
        );

        String str = error.toString();

        assertTrue(str.contains("error message"));
        assertTrue(str.contains("error details"));
    }
}
