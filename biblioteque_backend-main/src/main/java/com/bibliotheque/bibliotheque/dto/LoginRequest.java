package com.bibliotheque.bibliotheque.dto;

import lombok.Data;

/**
 * DTO pour la requête de login.
 * Sert uniquement à transporter email et mot de passe pour l'authentification.
 * 
 * Auteur: Groupe 1
 * Version: 1.0
 */
@Data
public class LoginRequest {

    /** Email de l'utilisateur */
    private String email;

    /** Mot de passe de l'utilisateur */
    private String password;
}