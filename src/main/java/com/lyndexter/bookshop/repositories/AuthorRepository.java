package com.lyndexter.bookshop.repositories;

import com.lyndexter.bookshop.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {}
