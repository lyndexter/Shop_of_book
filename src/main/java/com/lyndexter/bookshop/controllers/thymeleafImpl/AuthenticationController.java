package com.lyndexter.bookshop.controllers.thymeleafImpl;

import com.lyndexter.bookshop.configurations.security.JwtTokenUtil;
import com.lyndexter.bookshop.dto.UserDto;
import com.lyndexter.bookshop.models.User;
import io.swagger.annotations.Api;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Authentication")
@RestController
@RequestMapping("book_shop/view")
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenUtil jwtTokenUtil;

  public AuthenticationController(
      AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
    this.authenticationManager = authenticationManager;
    this.jwtTokenUtil = jwtTokenUtil;
  }

  @PostMapping("/login")
  public ResponseEntity<User> login(UserDto user) {
    try {
      Authentication authenticate =
          authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

      User userAuthenticated = (User) authenticate.getPrincipal();

      return ResponseEntity.ok()
          .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(userAuthenticated))
          .body(userAuthenticated);
    } catch (BadCredentialsException ex) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }
}
