package com.bibliotheque.bibliotheque.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bibliotheque.bibliotheque.entity.User;


public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User>  findByEmail(String email);
    
}
