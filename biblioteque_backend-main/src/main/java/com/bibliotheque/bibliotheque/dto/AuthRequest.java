package com.bibliotheque.bibliotheque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO pour la requête d'authentification (login) ou d'inscription.
 * Contient les informations nécessaires à l'authentification d'un utilisateur.
 * 
 * Auteur: Groupe 1
 * Version: 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    /** Email de l'utilisateur */
    private String email;

    /** Mot de passe de l'utilisateur */
    private String password;
}
