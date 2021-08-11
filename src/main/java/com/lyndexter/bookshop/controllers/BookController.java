package com.lyndexter.bookshop.controllers;

import com.lyndexter.bookshop.models.Book;
import com.lyndexter.bookshop.services.implementations.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book_shop/book")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Book> getBook(@PathVariable Integer id) {
    return new ResponseEntity<>(bookService.getEntity(id), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<Book>> getBooks() {
    return new ResponseEntity<>(bookService.getEntities(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Book> createBook(@RequestBody Book book) {
    book = bookService.createEntity(book);
    return new ResponseEntity<>(book, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable Integer id) {
    book = bookService.updateEntity(book, id);
    return new ResponseEntity<>(book, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Book> deleteBook(@PathVariable Integer id) {
    bookService.deleteEntity(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
