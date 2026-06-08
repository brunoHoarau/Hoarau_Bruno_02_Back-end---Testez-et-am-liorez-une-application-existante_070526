package com.openclassrooms.etudiant.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.openclassrooms.etudiant.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Repository permettant d'accéder aux données des étudiants.
 *
 * Fournit les opérations CRUD standard via JpaRepository.
 *
 * @see Student
 */
public interface StudentRepository extends JpaRepository<Student, Long>{

}
