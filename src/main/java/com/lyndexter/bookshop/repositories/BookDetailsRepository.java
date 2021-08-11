package com.lyndexter.bookshop.repositories;

import com.lyndexter.bookshop.models.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDetailsRepository extends JpaRepository<BookDetails, Integer> {}
