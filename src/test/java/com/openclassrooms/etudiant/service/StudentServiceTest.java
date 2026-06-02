package com.openclassrooms.etudiant.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.etudiant.entities.Student;
import com.openclassrooms.etudiant.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
	 @InjectMocks
	 private StudentService studentService;

	 @Mock
	 private StudentRepository studentRepository;

	    // Verifie creation student 
	 @Test
	 void shouldCreateStudent() {

	    // Given
	    Student student = new Student();
	    student.setFirstName("John");
	    student.setLastName("Doe");
	    student.setEmail("john@mail.com");

	    Mockito.when(studentRepository.save(Mockito.any(Student.class)))
	            .thenReturn(student);

	    // When
	    Student result = studentService.create(student);

	    // Then
	    Assertions.assertNotNull(result);
	    Assertions.assertEquals("John", result.getFirstName());
	    Assertions.assertEquals("Doe", result.getLastName());
	    }
	 
	 	// test recupération AllStudent
	 	@Test
	    void shouldReturnAllStudents() {

	 		 // Given
	 	    Student s1 = new Student();
	 	    s1.setId(1L);
	 	    s1.setFirstName("John");
	 	    s1.setLastName("Doe");
	 	    s1.setEmail("john@mail.com");

	 	    Student s2 = new Student();
	 	    s2.setId(2L);
	 	    s2.setFirstName("Jane");
	 	    s2.setLastName("Doe");
	 	    s2.setEmail("jane@mail.com");

	 	    List<Student> students = List.of(s1, s2);
	 	    
	        Mockito.when(studentRepository.findAll())
	                .thenReturn(students);

	        // When
	        List<Student> result = studentService.findAll();

	        // Then
	        Assertions.assertEquals(2, result.size());
	    }
	 	
	 // Test recup student byId
	 	@Test
	    void shouldReturnStudentById() {

	 		// Given
		    Student student = new Student();
		    student.setFirstName("John");
		    student.setLastName("Doe");
		    student.setEmail("john@mail.com");

	        Mockito.when(studentRepository.findById(1L))
	                .thenReturn(Optional.of(student));

	        // When
	        Student result = studentService.findById(1L);

	        // Then
	        Assertions.assertNotNull(result);
	        Assertions.assertEquals("John", result.getFirstName());
	    }
	 	
	 // Vérifie update student
	    @Test
	    void shouldUpdateStudent() {

	    	// Given
		    Student student = new Student();
		    student.setFirstName("John");
		    student.setLastName("Doe");
		    student.setEmail("john@mail.com");

	        Student updatedStudent = new Student();
	        updatedStudent.setFirstName("Updated");
	        updatedStudent.setLastName("Name");
	        updatedStudent.setEmail("updated@mail.com");

	        Mockito.when(studentRepository.findById(1L))
	                .thenReturn(Optional.of(student));

	        Mockito.when(studentRepository.save(Mockito.any(Student.class)))
	                .thenReturn(student);

	        // When
	        Student result = studentService.update(1L, updatedStudent);

	        // Then
	        Assertions.assertEquals("Updated", result.getFirstName());
	        Assertions.assertEquals("Name", result.getLastName());
	    }
}
