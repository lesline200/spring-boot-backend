package com.bibliotheque.bibliotheque.service;

import org.springframework.stereotype.Service;
import java.util.*;
import lombok.RequiredArgsConstructor;
import com.bibliotheque.bibliotheque.repository.BookRepository;
import com.bibliotheque.bibliotheque.entity.Book;
import com.bibliotheque.bibliotheque.exception.ResourceNotFoundException;


/**
 * Service gérant les opérations CRUD et l'authentification des Livres.
 * 
 * @author Groupe 1
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class BookService {

     private final BookRepository bookRepository;


     /** Récupère tous les livres */
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    /**
     * Récupère un livre par son identifiant.
     * @param id identifiant du livre
     * @return livre correspondant
     * @throws ResourceNotFoundException si le livre n'existe pas
     */
    public Book getBookById(Long id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livre introuvable avec id " + id));
    }


    /**
     * Sauvegarde un livre e
     * @param Book livre à sauvegarder
     * @return livre sauvegardé
     */
    public Book saveBook(Book book){
        return bookRepository.save(book);
    }


    /**
     * Supprime un livre par son identifiant.
     * @param id identifiant du livre
     */
    public void deleteBook(Long id){

        bookRepository.deleteById(id);
    }
    
    
}
