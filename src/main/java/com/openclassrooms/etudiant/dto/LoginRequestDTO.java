package com.openclassrooms.etudiant.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {
	//utilisation des NotBlank
	@NotBlank
    private String login;
	@NotBlank
    private String password;
    
    public String getLogin() {
    	return login;
    }
    
    public void setLogin(String login) {
    	this.login =  login;
    }
    
    public String getPassword() {
    	return password;
    }
    
    public void setPassword(String password) {
    	this.password =  password;
    }
}
