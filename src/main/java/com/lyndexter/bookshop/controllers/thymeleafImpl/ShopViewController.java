package com.lyndexter.bookshop.controllers.thymeleafImpl;

import com.lyndexter.bookshop.models.User;
import com.lyndexter.bookshop.repositories.UserRepository;
import com.lyndexter.bookshop.services.implementations.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("book_shop/view")
public class ShopViewController {

  private final BookService bookService;
  private final UserRepository userRepository;

  public ShopViewController(BookService bookService, UserRepository userRepository) {
    this.bookService = bookService;
    this.userRepository = userRepository;
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

  @GetMapping("books")
  public String showBooksPage(Model model) {
    model.addAttribute("books", bookService.getEntities());

    return "book-page";
  }

  @GetMapping("users")
  public ResponseEntity<User> showUser() {

    return new ResponseEntity<>(userRepository.findByUsername("user").get(), HttpStatus.OK);
  }
}
