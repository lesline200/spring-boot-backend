package com.bibliotheque.bibliotheque.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Représente un emprunt de l'application biblio.
 * Contient les information sur l'emprunt.
 * 
 * @author Groupe 1
 * @version 1.0
 */
@Entity
@Table(name = "borrows")
@Getter
@Setter
public class Borrow {

    /** Identifiant unique de l'emprunt */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** date de l'emprunt */
    private LocalDate dateEmprunt;

    /** date de retour prevue */
    private LocalDate dateRetourPrevue;

    /** date de retour Reelle */
    private LocalDate dateRetourReelle;

    /** statut de l'emprunt */
    private String statut;

    /** Penalite si le retout est effectuer apres la date prevue */
    private double penalite;


    /** Utilisateur ayant effectue l'emprunt' */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    /** Livre concerne par l'emprunt */
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    
}
