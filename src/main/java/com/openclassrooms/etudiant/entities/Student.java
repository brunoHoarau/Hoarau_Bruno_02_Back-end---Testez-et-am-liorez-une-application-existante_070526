package com.openclassrooms.etudiant.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    /**
     * Constructeur Student.
     */
    public Student() {
    }

    /**
     * Retourne id etudiant.
     *
     * @return l'id etudiant
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit ID etudiant.
     *
     * @param id ID à affecter
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Retourne prénom étudiant.
     *
     * @return prénom étudiant
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Définit prénom étudiant.
     *
     * @param le prénom de l'étudiant
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Définit nom étudiant.
     *
     * @return LastName le nom 
     */
    public String getLastName() {
        return lastName;
    }

    
    /**
     * Définit nom étudiant.
     *
     * @param lastName nom étudiant
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retourne email étudiant.
     *
     * @return email étudiant
     */
    public String getEmail() {
        return email;
    }

    
    /**
     * Définit email étudiant.
     *
     * @param email email etudiant
     */
    public void setEmail(String email) {
        this.email = email;
    }
}