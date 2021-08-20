package com.lyndexter.bookshop.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "book_details", schema = "book_shop_db", catalog = "")
public class BookDetails {
  private int id;
  private int count;
  private Book book;
  private Shop shop;

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
  @Column(name = "count")
  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BookDetails that = (BookDetails) o;
    return id == that.id && count == that.count;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, count);
  }

  @ManyToOne
  @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  @ManyToOne
  @JoinColumn(name = "Shop_id", referencedColumnName = "id", nullable = false)
  public Shop getShop() {
    return shop;
  }

  public void setShop(Shop shop) {
    this.shop = shop;
  }
}
