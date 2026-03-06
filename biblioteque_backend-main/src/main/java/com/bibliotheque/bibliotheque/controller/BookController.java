package com.bibliotheque.bibliotheque.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bibliotheque.bibliotheque.entity.Book;
import com.bibliotheque.bibliotheque.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * Controller exposant les endpoints REST pour gérer les Livres.
 * Permet CRUD complet et récupération par ID.
 * Swagger est utilisé pour générer la documentation API.
 * 
 * @author Groupe 1
 * @version 1.0
 */

@CrossOrigin(origins  = "*")
@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Tag(name = "Livres", description = "API de gestion des Livres")
public class BookController {

     private final BookService bookService;

    @Operation(summary = "Récupérer tous les livres")
    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @Operation(summary = "Récupérer un livre precis")
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @Operation(summary = "Ajouter un livre ")
    @PostMapping
    public Book addBook(@Valid @RequestBody Book book){
        return bookService.saveBook(book);
    }

    @Operation(summary = "Supprimer un livre precis")
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }

    @Operation(summary = "Mettre a jour  un livre precis")
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book){
         Book existing = bookService.getBookById(id);
         existing.setTitre(book.getTitre());
         existing.setAuteur(book.getAuteur());
         return bookService.saveBook(existing);
    }


    
}
