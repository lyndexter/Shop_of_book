package com.lyndexter.bookshop.dto;

import com.lyndexter.bookshop.models.Author;
import com.lyndexter.bookshop.models.Book;
import lombok.Data;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.Collection;

@Data
public class AuthorDto extends RepresentationModel<AuthorDto> {

  private Integer id;
  private String name;
  private String surname;
  private Integer age;
  private String nationality;

  public AuthorDto(Link initialLink, Author author) {
    add(initialLink);
    this.id = author.getId();
    this.name = author.getName();
    this.surname = author.getSurname();
    this.age = author.getAge();
    this.nationality = author.getNationality();
  }
}
