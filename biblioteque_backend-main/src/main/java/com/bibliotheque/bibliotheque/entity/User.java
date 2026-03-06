package com.bibliotheque.bibliotheque.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Représente un utilisateur de l'application biblio.
 * Implémente UserDetails pour l'intégration avec Spring Security.
 * Contient les informations personnelles et le rôle de l'utilisateur.
 * 
 * @author Groupe 1
 * @version 1.0
 */
@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements UserDetails {

    /** Identifiant unique de l'utilisateur */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nom complet de l'utilisateur */
    @Column(nullable = false)
    private String nom;

    /** Email unique utilisé pour l'authentification */
    @Column(nullable = false)
    private String email;

    /** Mot de passe encodé */
    @Column(nullable = false)
    private String password;

    /** Date d'inscription */
    private LocalDateTime dateInscription;

    /** Rôle de l'utilisateur */
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    /** Retourne la liste des autorités/granted authorities de l'utilisateur */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getNom()));
    }

    /** Retourne l'email comme nom d'utilisateur */
    @Override
    public String getUsername() { return email; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}