package com.lyndexter.bookshop.configurations;

import com.lyndexter.bookshop.configurations.security.filters.JwtTokenFilter;
import com.lyndexter.bookshop.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;

import static java.lang.String.format;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private UserRepository userRepository;
  private final JwtTokenFilter jwtTokenFilter;

  public SecurityConfig(UserRepository userRepository, JwtTokenFilter jwtTokenFilter) {
    this.userRepository = userRepository;
    this.jwtTokenFilter = jwtTokenFilter;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    //    http.authorizeRequests()
    //        .antMatchers("/book_shop/view/books")
    //        .hasRole("ADMIN")
    //        .antMatchers("/", "/**")
    //        .permitAll()
    //        .and()
    //        .formLogin()
    //        .loginPage("/book_shop/view/login")
    //        .loginProcessingUrl("/login")
    //        .defaultSuccessUrl("/book_shop/view/home");

//    http = http.cors().and().csrf().disable();
    http = http.csrf().disable();
//    http =
//        http.sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and();
//            .exceptionHandling()
//            .authenticationEntryPoint(
//                (request, response, ex) -> {
//                  response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
//                })
//            .and();
    http.authorizeRequests()
        .antMatchers("/book_shop/view/home")
        .permitAll()
        .antMatchers("/book_shop/view/signup")
        .permitAll()
        .antMatchers("/book_shop/view/login")
        .permitAll()
        .antMatchers("/book_shop/view/users")
        .permitAll()
        .antMatchers("/swagger-ui/", "/swagger-ui/**")
        .permitAll()
        .antMatchers("/swagger-resources/", "/swagger-resources/**")
        .permitAll()
        .antMatchers("/v2/api-docs/", "/v2/api-docs/**")
        .permitAll()
        .anyRequest()
        .authenticated().and().oauth2Login().permitAll();

//    http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //    auth.inMemoryAuthentication()
    //        .withUser("user")
    //        .password(passwordEncoder().encode("1111"))
    //        .roles("ADMIN");
//    auth.userDetailsService(
//        username ->
//            userRepository
//                .findByUsername(username)
//                .orElseThrow(
//                    () -> new UsernameNotFoundException(format("User: %s, not found", username))));
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowCredentials(true);
    configuration.addAllowedOrigin("*");
    configuration.addAllowedHeader("*");
    configuration.addAllowedMethod("*");
    source.registerCorsConfiguration("/**", configuration);
    return new CorsFilter(source);
  }
}
