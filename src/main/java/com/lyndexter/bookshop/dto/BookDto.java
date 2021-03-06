package com.lyndexter.bookshop.dto;

import com.lyndexter.bookshop.models.Book;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode
public class BookDto extends RepresentationModel<BookDto> {

  private Integer id;
  private String name;
  private Integer year;
  private Integer numberOfPages;

  public BookDto(Link initialLink, Book book) {
    add(initialLink);
    this.id = book.getId();
    this.name = book.getName();
    this.year = book.getYear();
    this.numberOfPages = book.getNumberOfPages();
  }
}
