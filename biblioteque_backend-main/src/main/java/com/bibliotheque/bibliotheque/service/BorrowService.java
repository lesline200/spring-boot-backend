package com.bibliotheque.bibliotheque.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.bibliotheque.bibliotheque.entity.Borrow;
import com.bibliotheque.bibliotheque.repository.BookRepository;
import com.bibliotheque.bibliotheque.repository.BorrowRepository;
import com.bibliotheque.bibliotheque.repository.UserRepository;
import com.bibliotheque.bibliotheque.entity.Book;
import com.bibliotheque.bibliotheque.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.bibliotheque.bibliotheque.exception.BadRequestException;


/**
 * Service gérant les emprunts et leur Retours.
 * 
 * @author Groupe 1
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Transactional
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;


    /**
     * Encode un mot de passe brut.
     * @param userId l'identifiant du livre.
     * @param bookId l'identifiant du livre.
     * @return un emprunt
     */
   public Borrow emprunterLivre(Long userId, Long bookId){
     
    Book book = bookRepository.findById(bookId).orElseThrow(()-> new RuntimeException("Livre introuvable"));
    if(book.getQuantity()<=0){
        throw new BadRequestException("Stock insuffisant pour ce livre");
    }
    User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("utilisateur introuvable"));

    book.setQuantity(book.getQuantity()-1);

    Borrow borrow = new Borrow();
    borrow.setUser(user);
    borrow.setBook(book);
    borrow.setDateEmprunt(LocalDate.now());
    borrow.setDateRetourPrevue(LocalDate.now().plusDays(14));
    borrow.setStatut("EN_COURS");

    return borrowRepository.save(borrow);
    

   }


   /**
     * Encode un mot de passe brut.
     * @param borrowId identifiant de l'emprunt.
     * @return un emprunt
     */
   public Borrow retournerLivre(Long borrowId){

    Borrow borrow = borrowRepository.findById(borrowId).orElseThrow();
    borrow.setDateRetourReelle(LocalDate.now());
    borrow.setStatut(("RETOURNE"));

    if(borrow.getDateRetourReelle().isAfter(borrow.getDateRetourPrevue())){
        long joursRetard = ChronoUnit.DAYS.between(
            borrow.getDateRetourPrevue(),
            borrow.getDateRetourReelle()
        );
        borrow.setPenalite(joursRetard *2);
    }
    Book book = borrow.getBook();
    book.setQuantity(book.getQuantity()+1);

    return borrowRepository.save(borrow);
   }

    
}
