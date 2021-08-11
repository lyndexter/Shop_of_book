package com.lyndexter.bookshop.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Shop {
  private int id;
  private String name;
  private String location;
  private Collection<BookDetails> booksDetails;

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
  @Column(name = "location")
  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Shop shop = (Shop) o;
    return id == shop.id
        && Objects.equals(name, shop.name)
        && Objects.equals(location, shop.location);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, location);
  }

  @OneToMany(mappedBy = "shop")
  public Collection<BookDetails> getBooksDetails() {
    return booksDetails;
  }

  public void setBooksDetails(Collection<BookDetails> booksDetails) {
    this.booksDetails = booksDetails;
  }
}
