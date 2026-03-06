package com.bibliotheque.bibliotheque.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bibliotheque.bibliotheque.entity.User;
import com.bibliotheque.bibliotheque.repository.UserRepository;
import com.bibliotheque.bibliotheque.security.JwtService;

import lombok.RequiredArgsConstructor;

/**
 * Service gérant l'authentification et l'inscription des utilisateurs.
 * Fournit des méthodes pour enregistrer un utilisateur et générer des tokens JWT.
 * 
 * Auteur: Groupe 1
 * Version: 1.0
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;
    public final JwtService jwtService;

    /**
     * Enregistre un nouvel utilisateur dans la base et retourne un token JWT.
     * @param user utilisateur à enregistrer
     * @return token JWT généré pour l'utilisateur
     */
    public String register(User user){
        // Encode le mot de passe avant la sauvegarde
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        // Génération du token JWT
        return jwtService.generateToken(user);
    }

    /**
     * Authentifie un utilisateur et retourne un token JWT.
     * @param email email de l'utilisateur
     * @param password mot de passe en clair
     * @return token JWT si authentification réussie
     * @throws RuntimeException si les identifiants sont incorrects
     */
    public String login(String email, String password){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Bad credentials");
        }

        return jwtService.generateToken(user);
    }
}