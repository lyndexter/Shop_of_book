package com.lyndexter.bookshop.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Author {
  private int id;
  private String name;
  private String surname;
  private Integer age;
  private String nationality;
  private Collection<Book> books;

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
  @Column(name = "surname")
  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  @Basic
  @Column(name = "age")
  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Basic
  @Column(name = "nationality")
  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Author author = (Author) o;
    return id == author.id
        && age == author.age
        && Objects.equals(name, author.name)
        && Objects.equals(surname, author.surname)
        && Objects.equals(nationality, author.nationality);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, surname, age, nationality);
  }

  @OneToMany(mappedBy = "author")
  public Collection<Book> getBooks() {
    return books;
  }

  public void setBooks(Collection<Book> books) {
    this.books = books;
  }
}
