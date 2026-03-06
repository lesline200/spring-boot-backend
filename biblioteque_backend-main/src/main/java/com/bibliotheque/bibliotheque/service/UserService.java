package com.bibliotheque.bibliotheque.service;

import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.bibliotheque.bibliotheque.entity.User;
import com.bibliotheque.bibliotheque.entity.Role;
import com.bibliotheque.bibliotheque.exception.ResourceNotFoundException;
import com.bibliotheque.bibliotheque.repository.UserRepository;
import com.bibliotheque.bibliotheque.repository.RoleRepository;
import lombok.RequiredArgsConstructor;

/**
 * Service gérant les opérations CRUD et l'authentification des utilisateurs.
 * 
 * @author Groupe 1
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    /**
     * Encode un mot de passe brut.
     * @param rawPassword mot de passe en clair
     * @return mot de passe encodé
     */
    public String encodePassword(String rawPassword){
        return passwordEncoder.encode(rawPassword);
    }

    /** Récupère tous les utilisateurs */
    public List<User> getAllUsers(){ return userRepository.findAll(); }

    /**
     * Sauvegarde un utilisateur en encodant le mot de passe
     * et en récupérant le rôle correspondant en base.
     * @param user utilisateur à sauvegarder
     * @return utilisateur sauvegardé
     */
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByNom(user.getRole().getNom())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);
        return userRepository.save(user);
    }

    /**
     * Supprime un utilisateur par son identifiant.
     * @param id identifiant de l'utilisateur
     */
    public void deleteUser(Long id){ userRepository.deleteById(id); }

    /**
     * Récupère un utilisateur par son identifiant.
     * @param id identifiant de l'utilisateur
     * @return utilisateur correspondant
     * @throws ResourceNotFoundException si l'utilisateur n'existe pas
     */
    public User getUserById(Long id){
        return userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé"));
    }

    /**
     * Met à jour un utilisateur existant.
     * @param id identifiant de l'utilisateur
     * @param user données mises à jour
     * @return utilisateur mis à jour
     */
    public User updateUser(Long id, User user){
        User existing = getUserById(id);
        existing.setNom(user.getNom());
        existing.setEmail(user.getEmail());
        if(user.getPassword() != null && !user.getPassword().isBlank()){
            existing.setPassword(encodePassword(user.getPassword()));
        }
        return userRepository.save(existing);
    }

    /**
     * Authentifie un utilisateur avec email et mot de passe.
     * @param email email de l'utilisateur
     * @param rawPassword mot de passe en clair
     * @return utilisateur authentifié
     * @throws RuntimeException si email ou mot de passe incorrect
     */
    public User login(String email, String rawPassword){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        if(passwordEncoder.matches(rawPassword, user.getPassword())){
            return user;
        } else {
            throw new RuntimeException("Mot de passe incorrect");
        }
    }
}