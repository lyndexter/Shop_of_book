package com.lyndexter.bookshop.controllers;

import com.lyndexter.bookshop.models.Author;
import com.lyndexter.bookshop.services.implementations.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book_shop/author")
public class AuthorController {

  private final AuthorService authorService;

  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Author> getAuthor(@PathVariable Integer id) {
    return new ResponseEntity<>(authorService.getEntity(id), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<Author>> getAuthors() {
    return new ResponseEntity<>(authorService.getEntities(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
    author = authorService.createEntity(author);
    return new ResponseEntity<>(author, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Author> updateAuthor(@RequestBody Author author, @PathVariable Integer id) {
    author = authorService.updateEntity(author, id);
    return new ResponseEntity<>(author, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Author> deleteAuthor(@PathVariable Integer id) {
    authorService.deleteEntity(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
