package com.lyndexter.bookshop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@ToString
@Entity
public class Book implements Serializable {
  private int id;
  private String name;
  private Integer year;
  private Integer numberOfPages;
  @JsonIgnore private Author author;
  @JsonIgnore private Collection<BookDetails> booksDetails;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Basic
  @Column(name = "year")
  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  @Basic
  @Column(name = "number_of_pages")
  public Integer getNumberOfPages() {
    return numberOfPages;
  }

  public void setNumberOfPages(Integer numberOfPages) {
    this.numberOfPages = numberOfPages;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Book book = (Book) o;
    return id == book.id
        && year == book.year
        && Objects.equals(name, book.name)
        && Objects.equals(numberOfPages, book.numberOfPages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, year, numberOfPages);
  }

  @ManyToOne
  @JoinColumn(name = "author_id", referencedColumnName = "id")
  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  @OneToMany(mappedBy = "book")
  public Collection<BookDetails> getBooksDetails() {
    return booksDetails;
  }

  public void setBooksDetails(Collection<BookDetails> booksDetails) {
    this.booksDetails = booksDetails;
  }
}
