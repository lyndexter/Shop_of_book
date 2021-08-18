package com.lyndexter.bookshop.controllers.thymeleafImpl;

import com.lyndexter.bookshop.exeptions.NotFoundEntityException;
import com.lyndexter.bookshop.models.User;
import com.lyndexter.bookshop.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.HashSet;

@RestController
@RequestMapping("/api/users")
@Slf4j
@Validated
class UserController {

  private final UserRepository repository;

  private final PasswordEncoder passwordEncoder;

  UserController(UserRepository repository, PasswordEncoder passwordEncoder) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
  }

  //    @GetMapping
  //    Page<User> all(@PageableDefault(size = Integer.MAX_VALUE) Pageable pageable,
  // OAuth2Authentication authentication) {
  //        String auth = (String) authentication.getUserAuthentication().getPrincipal();
  //        String role = authentication.getAuthorities().iterator().next().getAuthority();
  //        if (role.equals(User.Role.USER.name())) {
  //            return repository.findAllByEmail(auth, pageable);
  //        }
  //        return repository.findAll(pageable);
  //    }
  //
  //    @GetMapping("/search")
  //    Page<User> search(@RequestParam String email, Pageable pageable, OAuth2Authentication
  // authentication) {
  //        String auth = (String) authentication.getUserAuthentication().getPrincipal();
  //        String role = authentication.getAuthorities().iterator().next().getAuthority();
  //        if (role.equals(User.Role.USER.name())) {
  //            return repository.findAllByEmailContainsAndEmail(email, auth, pageable);
  //        }
  //        return repository.findByEmailContains(email, pageable);
  //    }

  @GetMapping("/findByEmail")
  @PreAuthorize("!hasAuthority('USER') || (authentication.principal == #email)")
  User findByEmail(@RequestParam String email, OAuth2Authentication authentication) {
    return repository
        .findByUsername(email)
        .orElseThrow(
            () -> new NotFoundEntityException("Not found user with username: " + email.toString()));
  }

  @GetMapping("/{id}")
  @PostAuthorize(
      "!hasAuthority('USER') || (returnObject != null && returnObject.email == authentication.principal)")
  User one(@PathVariable Integer id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new NotFoundEntityException("Not found user with id: " + id.toString()));
  }

  @PutMapping("/{id}")
  @PreAuthorize(
      "!hasAuthority('USER') || (authentication.principal == @userRepository.findById(#id).orElse(new net.reliqs.gleeometer.users.User()).email)")
  void update(@PathVariable Integer id, @Valid @RequestBody User res) {
    User u =
        repository
            .findById(id)
            .orElseThrow(
                () -> new NotFoundEntityException("Not found user with id: " + id.toString()));
    res.setPassword(u.getPassword());
    repository.save(res);
  }

  @PostMapping
  @PreAuthorize("!hasAuthority('USER')")
  User create(@Valid @RequestBody User res) {
    return repository.save(res);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("!hasAuthority('USER')")
  void delete(@PathVariable Integer id) {
    if (repository.existsById(id)) {
      repository.deleteById(id);
    } else {
      throw new NotFoundEntityException("Not found user with id: " + id.toString());
    }
  }

  @PutMapping("/{id}/changePassword")
  @PreAuthorize(
      "!hasAuthority('USER') || (#oldPassword != null && !#oldPassword.isEmpty() && authentication.principal == @userRepository.findById(#id).orElse(new net.reliqs.gleeometer.users.User()).email)")
  void changePassword(
      @PathVariable Integer id,
      @RequestParam(required = false) String oldPassword,
      @Valid @Size(min = 3) @RequestParam String newPassword) {
    User user =
        repository
            .findById(id)
            .orElseThrow(
                () -> new NotFoundEntityException("Not found user with id: " + id.toString()));
    if (oldPassword == null
        || oldPassword.isEmpty()
        || passwordEncoder.matches(oldPassword, user.getPassword())) {
      user.setPassword(passwordEncoder.encode(newPassword));
      repository.save(user);
    } else {
      throw new ConstraintViolationException("old password doesn't match", new HashSet<>());
    }
  }
}
