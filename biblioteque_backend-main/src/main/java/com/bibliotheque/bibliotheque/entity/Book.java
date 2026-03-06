package com.bibliotheque.bibliotheque.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


/**
 * Représente un livre de l'application biblio.
 * Contient les informations sur les caracteristique d'un livre.
 * 
 * @author Groupe 1
 * @version 1.0
 */

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book {

    /** Identifiant unique du livre */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** titre du livre qui doit etre automatiquement rempli */
    @NotBlank
    private String titre;

    /** auteurdu livre qui doit etre automatiquement rempli */
    @NotBlank
    private String auteur;
    private String categorie;

    /** le numero de la version  du livre qui doit etre unique pour chaque livre */
    @Column(unique = true)
    private String isbn;

    /** description du livre */
    private String description;

    /** la quantite du livre qui doit toujours exister */
    @Column(name = "quantite", nullable = false)
    private Integer quantity;

    /** disponibilite du livre 
     * si le livre est disponible alors true 
     * sinon alors false
     */
    private Boolean disponible;

    /** date d'ajout du livre */
    private LocalDateTime dateAjout;


    
}
