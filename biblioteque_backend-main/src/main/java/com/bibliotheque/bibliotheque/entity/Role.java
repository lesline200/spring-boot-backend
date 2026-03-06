package com.bibliotheque.bibliotheque.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Représente un rôle d'utilisateur dans l'application bibliothèque.
 * Chaque rôle possède un nom unique (ex : "ADMIN", "USER").
 * Les rôles sont utilisés pour gérer les droits et permissions des utilisateurs.
 * 
 * Auteur: Maeva
 * Version: 1.0
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    /** Identifiant unique du rôle */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nom unique du rôle (ex: ADMIN, USER) */
    @Column(nullable = false, unique = true)
    private String nom;
}