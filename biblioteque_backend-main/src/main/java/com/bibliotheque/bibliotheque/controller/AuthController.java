package com.bibliotheque.bibliotheque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.bibliotheque.bibliotheque.dto.AuthRequest;
import com.bibliotheque.bibliotheque.entity.User;
import com.bibliotheque.bibliotheque.service.UserService;
import com.bibliotheque.bibliotheque.security.JwtService;
import com.bibliotheque.bibliotheque.security.CustomUserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Controller exposant les endpoints d'authentification.
 * Gère la connexion et l'inscription des utilisateurs avec génération de token JWT.
 * 
 * Auteur: Groupe 1
 * Version: 1.0
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentification", description = "Endpoints pour login et register")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;   
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;

    /**
     * Représente la réponse après authentification avec le token JWT.
     * @param token token JWT généré
     */
    public record AuthResponse(String token) {}

    /**
     * Permet à un utilisateur de se connecter.
     * Génère un token JWT à retourner.
     * 
     * @param request DTO contenant email et mot de passe
     * @return ResponseEntity contenant le token JWT
     */
    @Operation(summary = "Permet à un utilisateur de se connecter et génère un token JWT")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        // Authentifie l'utilisateur
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );

        // Récupération des détails de l'utilisateur
        UserDetails user = customUserDetailsService.loadUserByUsername(request.getEmail());

        // Génération du token JWT
        String token = jwtService.generateToken(user); 

        return ResponseEntity.ok(new AuthResponse(token));
    }

    /**
     * Endpoint pour l'inscription d'un nouvel utilisateur.
     * 
     * @param user utilisateur à créer
     * @return ResponseEntity contenant l'utilisateur créé
     */
    @Operation(summary = "Inscription d'un utilisateur")
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        User created = userService.saveUser(user);
        return ResponseEntity.ok(created);
    }
}