package com.bibliotheque.bibliotheque.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bibliotheque.bibliotheque.service.BorrowService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import com.bibliotheque.bibliotheque.entity.Borrow;

/**
 * Controller exposant les endpoints REST pour gérer les emprunts.
 * Swagger est utilisé pour générer la documentation API.
 * 
 * @author Groupe 1
 * @version 1.0
 */
@RestController
@RequestMapping("/api/borrows")
@RequiredArgsConstructor
@Tag(name = "Emprunts", description = "API de gestion des emprunts")

public class BorrowController {

    private final BorrowService borrowService;

    @Operation(summary = "emprunter un livre")
    @PostMapping("/emprunter")
    public Borrow emprunter(@RequestParam Long userId,@RequestParam Long bookId){
        return borrowService.emprunterLivre(userId, bookId);
    }

    @Operation(summary = "retourner un livre")
    @PostMapping("/retourner")
    public Borrow retourner(@RequestParam Long borowId){
        return borrowService.retournerLivre(borowId);
    }
    
}
