package com.openclassrooms.etudiant.mapper;

import com.openclassrooms.etudiant.dto.StudentDTO;
import com.openclassrooms.etudiant.entities.Student;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private final StudentMapper mapper = new StudentMapper();

    // =========================
    // toDto
    // =========================
    @Test
    void shouldMapEntityToDto() {

        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john@mail.com");

        StudentDTO dto = mapper.toDto(student);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("John", dto.getFirstName());
        assertEquals("Doe", dto.getLastName());
        assertEquals("john@mail.com", dto.getEmail());
    }

    // =========================
    // toEntity
    // =========================
    @Test
    void shouldMapDtoToEntity() {

        StudentDTO dto = new StudentDTO();
        dto.setId(2L);
        dto.setFirstName("Jane");
        dto.setLastName("Smith");
        dto.setEmail("jane@mail.com");

        Student student = mapper.toEntity(dto);

        assertNotNull(student);
        assertEquals(2L, student.getId());
        assertEquals("Jane", student.getFirstName());
        assertEquals("Smith", student.getLastName());
        assertEquals("jane@mail.com", student.getEmail());
    }

    // =========================
    // toDtoList
    // =========================
    @Test
    void shouldMapEntityListToDtoList() {

        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John");

        List<StudentDTO> result = mapper.toDtoList(List.of(student));

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
    }
}