package com.openclassrooms.etudiant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Objet de transfert de données représentant un étudiant.
 *
 * Utilisé pour exposer les données de l'entité Student
 * via l'API sans exposer directement l'entité JPA.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

	/**
     * Identifiant de l'étudiant.
     */
    private Long id;

    /**
     * Prénom de l'étudiant.
     */
    private String firstName;

    /**
     * Nom de famille de l'étudiant.
     */
    private String lastName;

    /**
     * Adresse email de l'étudiant.
     */
    private String email;

}
