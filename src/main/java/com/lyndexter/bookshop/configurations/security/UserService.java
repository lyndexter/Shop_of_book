package com.lyndexter.bookshop.configurations.security;

import com.lyndexter.bookshop.exeptions.NotFoundEntityException;
import com.lyndexter.bookshop.models.User;
import com.lyndexter.bookshop.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService implements UserDetailsService {

  private final UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user =
        repository
            .findByUsername(username)
            .orElseThrow(() -> new NotFoundEntityException("User not found: " + username));
    GrantedAuthority authority = new SimpleGrantedAuthority("USER");
    return new org.springframework.security.core.userdetails.User(
        user.getUsername(), user.getPassword(), Arrays.asList(authority));
  }
}
