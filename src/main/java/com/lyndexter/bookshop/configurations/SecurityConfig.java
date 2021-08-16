package com.lyndexter.bookshop.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http.csrf()
//        .disable()
//        .authorizeRequests()
//        .antMatchers("/book_shop/view/books")
//        .hasRole("ADMIN")
//        .antMatchers("/book_shop")
//        .permitAll();
//  }

//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.inMemoryAuthentication().withUser("user").password("1111").roles("ADMIN");
//  }
}
