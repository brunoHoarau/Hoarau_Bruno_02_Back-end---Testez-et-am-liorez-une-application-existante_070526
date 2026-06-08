package com.openclassrooms.etudiant.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.etudiant.dto.StudentDTO;
import com.openclassrooms.etudiant.entities.Student;
import com.openclassrooms.etudiant.mapper.StudentMapper;
import com.openclassrooms.etudiant.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    
    /**
     * Crée un nouvel étudiant.
     *
     * @param dto datum student
     * @return student created
     */
    public StudentDTO create(StudentDTO dto) {
        Student student = studentMapper.toEntity(dto);
        Student saved = studentRepository.save(student);
        return studentMapper.toDto(saved);
    }

    
    /**
     * trouver étudiants pour list.
     *
     * @return list all students 
     */
    @Transactional(readOnly = true)
    public List<StudentDTO> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toDto)
                .toList();
    }
    
    
    /**
     * trouver étudiant par ID.
     *
     * @param id studnet
     * @return student 
     */
    @Transactional(readOnly = true)
    public StudentDTO findById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return studentMapper.toDto(student);
    }

    
    /**
     * MAJ étudiant 
     *
     * @param id, student datum
     * @return student 
     */
     public StudentDTO update(Long id, StudentDTO dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setFirstName(dto.getFirstName());
        student.setEmail(dto.getEmail());

        Student saved = studentRepository.save(student);
        return studentMapper.toDto(saved);
    }

     /**
      * supprimer étudiant par id 
      *
      * @param id
      */
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
