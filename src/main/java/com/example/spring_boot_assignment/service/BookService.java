package com.example.spring_boot_assignment.service;

import com.example.spring_boot_assignment.exception.ResourceNotFoundException;
import com.example.spring_boot_assignment.model.Book;
import com.example.spring_boot_assignment.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Add a new book to the database
     *
     * @param book Book to be added
     * @return The saved book with generated ID
     */
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    /**
     * Retrieve all books from the database
     *
     * @return List of all books
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Find a book by its ID
     *
     * @param id The ID of the book to find
     * @return The found book
     * @throws ResourceNotFoundException if book with given ID doesn't exist
     */
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    /**
     * Update an existing book's details
     *
     * @param id Book ID to update
     * @param bookDetails New book details
     * @return The updated book
     * @throws ResourceNotFoundException if book with given ID doesn't exist
     */
    public Book updateBook(Long id, Book bookDetails) {
        // First check if the book exists
        Book book = getBookById(id);

        // Update the book properties
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setPrice(bookDetails.getPrice());
        book.setPublishedDate(bookDetails.getPublishedDate());

        // Save and return the updated book
        return bookRepository.save(book);
    }

    /**
     * Delete a book by its ID
     *
     * @param id The ID of the book to delete
     * @throws ResourceNotFoundException if book with given ID doesn't exist
     */
    public void deleteBook(Long id) {
        // First check if the book exists
        Book book = getBookById(id);

        // Then delete it
        bookRepository.delete(book);
    }
}