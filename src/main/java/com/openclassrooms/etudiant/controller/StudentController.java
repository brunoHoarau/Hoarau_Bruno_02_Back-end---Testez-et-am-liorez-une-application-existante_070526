package com.openclassrooms.etudiant.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.etudiant.dto.StudentDTO;
import com.openclassrooms.etudiant.mapper.StudentMapper;
import com.openclassrooms.etudiant.entities.Student;
import com.openclassrooms.etudiant.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    // CREATE
    @PostMapping
    public ResponseEntity<StudentDTO> create(@RequestBody StudentDTO dto) {

        Student student = studentService.create(
                studentMapper.toEntity(dto)
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentMapper.toDto(student));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<StudentDTO>> findAll() {

        return ResponseEntity.ok(
                studentMapper.toDtoList(
                        studentService.findAll()
                )
        );
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> findById(@PathVariable Long id) {

        return ResponseEntity.ok(
                studentMapper.toDto(
                        studentService.findById(id)
                )
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(
            @PathVariable Long id,
            @RequestBody StudentDTO dto) {

        Student updated = studentService.update(
                id,
                studentMapper.toEntity(dto)
        );

        return ResponseEntity.ok(
                studentMapper.toDto(updated)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        studentService.delete(id);

        return ResponseEntity.noContent().build();
    }
}