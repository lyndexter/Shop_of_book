package com.lyndexter.bookshop.repositories;

import com.lyndexter.bookshop.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {}
