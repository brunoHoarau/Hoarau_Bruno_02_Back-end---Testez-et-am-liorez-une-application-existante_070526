package com.openclassrooms.etudiant.controller;

import com.openclassrooms.etudiant.dto.StudentDTO;
import com.openclassrooms.etudiant.entities.Student;
import com.openclassrooms.etudiant.mapper.StudentMapper;
import com.openclassrooms.etudiant.service.StudentService;
import com.openclassrooms.etudiant.service.JwtService;
import com.openclassrooms.etudiant.configuration.security.JwtAuthenticationFilter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private StudentMapper studentMapper;

    // 🔥 Security mocks obligatoires pour éviter JWT crash
    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserDetailsService userDetailsService;

    // =========================
    // CREATE
    // =========================
    @Test
    void shouldCreateStudent() throws Exception {

        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john@mail.com");

        StudentDTO dto = new StudentDTO();
        dto.setId(1L);
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setEmail("john@mail.com");

        given(studentMapper.toEntity(any())).willReturn(student);
        given(studentService.create(any())).willReturn(dto);
        given(studentMapper.toDto(any())).willReturn(dto);

        mockMvc.perform(post("/api/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "firstName":"John",
                            "lastName":"Doe",
                            "email":"john@mail.com"
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("john@mail.com"));
    }

    // =========================
    // READ ALL
    // =========================
    @Test
    void shouldReturnAllStudents() throws Exception {

        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John");

        StudentDTO dto = new StudentDTO();
        dto.setId(1L);
        dto.setFirstName("John");

        given(studentService.findAll()).willReturn(List.of(dto));
//        given(studentMapper.toDtoList(any())).willReturn(List.of(dto));

        mockMvc.perform(get("/api/student"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].firstName").value("John"));
    }

    // =========================
    // READ ONE
    // =========================
    @Test
    void shouldReturnStudentById() throws Exception {

        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John");

        StudentDTO dto = new StudentDTO();
        dto.setId(1L);
        dto.setFirstName("John");

        given(studentService.findById(1L)).willReturn(dto);
//        given(studentMapper.toDto(1L)).willReturn(dto);

        mockMvc.perform(get("/api/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    // =========================
    // DELETE
    // =========================
    @Test
    void shouldDeleteStudent() throws Exception {

        mockMvc.perform(delete("/api/student/1"))
                .andExpect(status().isNoContent());
    }

    // =========================
    // UPDATE
    // =========================
    @Test
    void shouldUpdateStudent() throws Exception {

        Student student = new Student();
        student.setId(1L);
        student.setFirstName("Jane");

        StudentDTO dto = new StudentDTO();
        dto.setId(1L);
        dto.setFirstName("Jane");

        given(studentService.update(eq(1L), any(StudentDTO.class))).willReturn(dto);
//        given(studentMapper.toDto(any())).willReturn(dto);

        mockMvc.perform(put("/api/student/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "firstName":"Jane",
                            "lastName":"Doe",
                            "email":"jane@mail.com"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jane"));
    }
}