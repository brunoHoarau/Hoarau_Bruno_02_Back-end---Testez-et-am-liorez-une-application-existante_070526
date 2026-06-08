package com.openclassrooms.etudiant.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/**
 * DTO représentant une requête de connexion utilisateur.
 *
 * Contient les informations nécessaires pour authentifier un utilisateur.
 */
@Data
public class LoginRequestDTO {
	 /**
     * Identifiant de connexion de l'utilisateur.
     */
    @NotBlank
    private String login;

    /**
     * Mot de passe de l'utilisateur.
     */
    @NotBlank
    private String password;

    /**
     * Retourne le login de l'utilisateur.
     *
     * @return le login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Définit le login de l'utilisateur.
     *
     * @param login le login à affecter
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Retourne le mot de passe de l'utilisateur.
     *
     * @return le mot de passe
     */
    public String getPassword() {
        return password;
    }

    /**
     * Définit le mot de passe de l'utilisateur.
     *
     * @param password le mot de passe à affecter
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
