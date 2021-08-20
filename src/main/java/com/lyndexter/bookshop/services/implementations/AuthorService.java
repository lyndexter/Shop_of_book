package com.lyndexter.bookshop.services.implementations;

import com.lyndexter.bookshop.exeptions.NotFoundEntityException;
import com.lyndexter.bookshop.models.Author;
import com.lyndexter.bookshop.repositories.AuthorRepository;
import com.lyndexter.bookshop.services.ImplementedService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends ImplementedService<Author, Integer> {

  private final JpaRepository<Author, Integer> authorRepository;

  public AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  @Override
  protected JpaRepository<Author, Integer> getRepository() {
    return authorRepository;
  }

  @Override
  protected Author changeEntity(Author changingEntity, Author entity) {
    changingEntity.setName(entity.getName() != null ? entity.getName() : changingEntity.getName());
    changingEntity.setAge(entity.getAge() != null ? entity.getAge() : changingEntity.getAge());
    changingEntity.setSurname(
        entity.getSurname() != null ? entity.getSurname() : changingEntity.getSurname());
    changingEntity.setNationality(
        entity.getNationality() != null
            ? entity.getNationality()
            : changingEntity.getNationality());

    return changingEntity;
  }

  @Override
  protected void throwExeption() {
    throw new NotFoundEntityException("No such author is present");
  }
}
