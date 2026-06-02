package com.openclassrooms.etudiant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.openclassrooms.etudiant.entities.Student;
import com.openclassrooms.etudiant.repository.StudentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentService {
	
    private final StudentRepository studentRepository;

    public Student create(com.openclassrooms.etudiant.entities.Student student) {
        return studentRepository.save(student);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));
    }

    public Student update(Long id, com.openclassrooms.etudiant.entities.Student student2) {

        Student student = findById(id);

        student.setFirstName(student2.getFirstName());
        student.setLastName(student2.getLastName());
        student.setEmail(student2.getEmail());

        return studentRepository.save(student);
    }

    public void delete(Long id) {

        Student student = findById(id);

        studentRepository.delete(student);
    }

}
