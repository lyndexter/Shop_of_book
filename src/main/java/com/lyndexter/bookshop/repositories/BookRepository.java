package com.lyndexter.bookshop.repositories;

import com.lyndexter.bookshop.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {}
