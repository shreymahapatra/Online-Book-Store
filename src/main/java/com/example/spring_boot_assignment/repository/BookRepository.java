package com.example.spring_boot_assignment.repository;

import com.example.spring_boot_assignment.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Spring Data JPA automatically implements basic CRUD operations
    // You can add custom query methods here if needed
}