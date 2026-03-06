package com.bibliotheque.bibliotheque.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.bibliotheque.bibliotheque.entity.User;
import com.bibliotheque.bibliotheque.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Controller exposant les endpoints REST pour gérer les utilisateurs.
 * Permet CRUD complet et récupération par ID.
 * Swagger est utilisé pour générer la documentation API.
 * 
 * @author Groupe 1
 * @version 1.0
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Utilisateurs", description = "API de gestion des utilisateurs")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Récupérer tous les utilisateurs")
    @GetMapping
    public List<User> getAllUsers(){ return userService.getAllUsers(); }

    @Operation(summary = "Créer un utilisateur")
    @PostMapping
    public User creatUser(@RequestBody User user){ return userService.saveUser(user); }

    @Operation(summary = "Supprimer un utilisateur")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){ userService.deleteUser(id); }

    @Operation(summary = "Récupérer un utilisateur par ID")
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){ return userService.getUserById(id); }

    @Operation(summary = "Mettre à jour un utilisateur")
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return userService.updateUser(id, user);
    }
}