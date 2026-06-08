package com.openclassrooms.etudiant.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.etudiant.dto.StudentDTO;
import com.openclassrooms.etudiant.mapper.StudentMapper;
import com.openclassrooms.etudiant.entities.Student;
import com.openclassrooms.etudiant.service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    /**
     * Crée un nouvel étudiant.
     *
     * @param dto les données de l'étudiant à créer
     * @return l'étudiant créé avec le statut HTTP 201 (CREATED)
     */
    @PostMapping
    public ResponseEntity<StudentDTO> create(@Valid @RequestBody StudentDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.create(dto));
    }

    /**
     * Récupère la liste de tous les étudiants.
     *
     * @return une liste d'étudiants avec le statut HTTP 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<StudentDTO>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    /**
     * Récupère un étudiant à partir de son identifiant.
     *
     * @param id identifiant de l'étudiant
     * @return l'étudiant correspondant avec le statut HTTP 200 (OK)
     */
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findById(id));
    }

    /**
     * Met à jour un étudiant existant.
     *
     * @param id identifiant de l'étudiant à modifier
     * @param dto nouvelles données de l'étudiant
     * @return l'étudiant mis à jour avec le statut HTTP 200 (OK)
     */
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody StudentDTO dto) {

        return ResponseEntity.ok(studentService.update(id, dto));
    }

    /**
     * Supprime un étudiant à partir de son identifiant.
     *
     * @param id identifiant de l'étudiant à supprimer
     * @return réponse vide avec le statut HTTP 204 (NO CONTENT)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}