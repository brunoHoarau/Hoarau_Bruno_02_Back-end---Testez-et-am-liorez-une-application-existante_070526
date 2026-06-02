package com.openclassrooms.etudiant.mapper;

import com.openclassrooms.etudiant.dto.StudentDTO;
import com.openclassrooms.etudiant.entities.Student;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentDTO toDto(Student student) {
        StudentDTO dto = new StudentDTO();

        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());

        return dto;
    }

    public Student toEntity(StudentDTO dto) {
        Student student = new Student();

        student.setId(dto.getId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());

        return student;
    }

    public List<StudentDTO> toDtoList(List<Student> students) {
        return students.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}