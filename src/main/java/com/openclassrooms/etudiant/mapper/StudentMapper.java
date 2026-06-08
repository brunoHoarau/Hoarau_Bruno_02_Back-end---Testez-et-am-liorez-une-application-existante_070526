package com.openclassrooms.etudiant.mapper;

import com.openclassrooms.etudiant.dto.StudentDTO;
import com.openclassrooms.etudiant.entities.Student;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;


/**
 * Mapper permettant de convertir les entités Student en DTO et inversement.
 *
 * Assure la transformation des objets entre la couche de persistance
 * et la couche exposée par l'API.
 */

@Component
public class StudentMapper {

	/**
     * Convertit une entité Student en StudentDTO.
     *
     * @param student l'entité Student à convertir
     * @return le DTO correspondant
     */
    public StudentDTO toDto(Student student) {
        StudentDTO dto = new StudentDTO();

        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());

        return dto;
    }

    
    /**
     * Convertit un StudentDTO en entité Student.
     *
     * @param dto le DTO à convertir
     * @return l'entité Student correspondante
     */
    public Student toEntity(StudentDTO dto) {
        Student student = new Student();

        student.setId(dto.getId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());

        return student;
    }

    /**
     * Convertit une liste d'entités Student en liste de StudentDTO.
     *
     * @param students liste d'entités Student
     * @return liste de DTO correspondants
     */
    public List<StudentDTO> toDtoList(List<Student> students) {
        return students.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}