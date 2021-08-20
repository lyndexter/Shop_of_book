package com.lyndexter.bookshop.controllers.thymeleafImpl;

import com.lyndexter.bookshop.configurations.security.JwtTokenUtil;
import com.lyndexter.bookshop.dto.UserDto;
import com.lyndexter.bookshop.models.User;
import com.lyndexter.bookshop.repositories.UserRepository;
import io.swagger.annotations.Api;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Api(tags = "Authentication")
//@RestController
//@RequestMapping("book_shop/view")
//public class AuthenticationController {
//
//  private final AuthenticationManager authenticationManager;
//  private final JwtTokenUtil jwtTokenUtil;
//  private final UserRepository userRepository;
//  private final PasswordEncoder passwordEncoder;
//
//  public AuthenticationController(
//      AuthenticationManager authenticationManager,
//      JwtTokenUtil jwtTokenUtil,
//      UserRepository userRepository,
//      PasswordEncoder passwordEncoder) {
//    this.authenticationManager = authenticationManager;
//    this.jwtTokenUtil = jwtTokenUtil;
//    this.userRepository = userRepository;
//    this.passwordEncoder = passwordEncoder;
//  }
//
//  @PostMapping("/login")
//  public ResponseEntity<?> login(@RequestBody UserDto user) {
//    try {
//      Authentication authenticate =
//          authenticationManager.authenticate(
//              new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//
//      User userAuthenticated = (User) authenticate.getPrincipal();
//
//      return ResponseEntity.ok()
//          .header(
//              HttpHeaders.AUTHORIZATION,
//              "Bearer " + jwtTokenUtil.generateAccessToken(userAuthenticated))
//          .body(userAuthenticated);
//    } catch (BadCredentialsException ex) {
//      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//          .body("Cant recognise user throw this credentials");
//    }
//  }
//
//  @PostMapping("/authenticate")
//  public ResponseEntity<?> loginFromForm(UserDto userDto) {
//    return login(userDto);
//  }
//
//  @PostMapping("/signup")
//  public ResponseEntity<?> signupFromForm(UserDto userDto) {
//    if (userRepository.existsByUsername(userDto.getUsername())) {
//      return ResponseEntity.badRequest().body("Error, username is already in use");
//    }
//
//    User user = new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()));
//    userRepository.save(user);
//    return ResponseEntity.ok()
//        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtTokenUtil.generateAccessToken(user))
//        .body(user);
//  }
//}
