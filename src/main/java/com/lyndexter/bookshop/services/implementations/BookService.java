package com.lyndexter.bookshop.services.implementations;

import com.lyndexter.bookshop.exeptions.NotFoundEntityException;
import com.lyndexter.bookshop.models.Book;
import com.lyndexter.bookshop.repositories.BookRepository;
import com.lyndexter.bookshop.services.ImplementedService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService extends ImplementedService<Book, Integer> {

  private final JpaRepository<Book, Integer> bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  protected JpaRepository<Book, Integer> getRepository() {
    return bookRepository;
  }

  @Override
  protected Book changeEntity(Book changingEntity, Book entity) {
    changingEntity.setAuthor(
        entity.getAuthor() != null ? entity.getAuthor() : changingEntity.getAuthor());
    changingEntity.setName(entity.getName() != null ? entity.getName() : changingEntity.getName());
    changingEntity.setYear(entity.getYear() != null ? entity.getYear() : changingEntity.getYear());
    changingEntity.setAuthor(
        entity.getAuthor() != null ? entity.getAuthor() : changingEntity.getAuthor());

    return changingEntity;
  }

  @Override
  protected void throwExeption() {
    throw new NotFoundEntityException("No such book is present");
  }
}
