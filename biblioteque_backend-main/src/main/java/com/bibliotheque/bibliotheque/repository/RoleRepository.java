package com.bibliotheque.bibliotheque.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bibliotheque.bibliotheque.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

    Optional<Role> findByNom(String name);

    
}
