package com.example.spring_boot_assignment.controller;

import com.example.spring_boot_assignment.model.Book;
import com.example.spring_boot_assignment.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Endpoint to add a new book
     *
     * @param book Book details from request body
     * @return Created book with HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        Book savedBook = bookService.saveBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    /**
     * Endpoint to retrieve all books
     *
     * @return List of all books
     */
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    /**
     * Endpoint to get a specific book by ID
     *
     * @param id Book ID from path variable
     * @return Book details
     */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    /**
     * Endpoint to update an existing book
     *
     * @param id Book ID from path variable
     * @param bookDetails Updated book details from request body
     * @return Updated book
     */
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book bookDetails) {
        Book updatedBook = bookService.updateBook(id, bookDetails);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    /**
     * Endpoint to delete a book
     *
     * @param id Book ID from path variable
     * @return No content response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}