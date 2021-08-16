package com.lyndexter.bookshop.controllers.thymeleafImpl;

import com.lyndexter.bookshop.services.implementations.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Slf4j
@Controller
@RequestMapping("book_shop/view")
public class ShopViewController {

  private final BookService bookService;

  public ShopViewController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/home")
  public String showHome() {
    return "home";
  }

  @GetMapping("/signup")
  public String showSignUpForm() {
    return "signup";
  }

  @GetMapping("/login")
  public String showLoginForm() {
    return "login";
  }

  //    @GetMapping("/{id}")
  //    public ResponseEntity<BookDto> getBook(@PathVariable Integer id) {
  //        Book book = bookService.getEntity(id);
  //        BookDto bookDto = createDto(book);
  //        return new ResponseEntity<>(bookDto, HttpStatus.OK);
  //    }
  //
  @GetMapping("books")
  public String showBooksPage(Model model) {
    model.addAttribute("books", bookService.getEntities());

    return "book-page";
  }

  //    @PostMapping
  //    public ResponseEntity<BookDto> createBook(@RequestBody Book book) {
  //        book = bookService.createEntity(book);
  //        BookDto bookDto = createDto(book);
  //        return new ResponseEntity<>(bookDto, HttpStatus.CREATED);
  //    }
  //
  //    @PutMapping("/{id}")
  //    public ResponseEntity<BookDto> updateBook(@RequestBody Book book, @PathVariable Integer id)
  // {
  //        book = bookService.updateEntity(book, id);
  //        BookDto bookDto = createDto(book);
  //        return new ResponseEntity<>(bookDto, HttpStatus.OK);
  //    }
  //
  //    @DeleteMapping("/{id}")
  //    public ResponseEntity<BookDto> deleteBook(@PathVariable Integer id) {
  //        bookService.deleteEntity(id);
  //        return new ResponseEntity<>(HttpStatus.OK);
  //    }
}
