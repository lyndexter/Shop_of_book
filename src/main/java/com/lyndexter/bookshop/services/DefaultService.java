package com.lyndexter.bookshop.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DefaultService<T, ID> {

  T getEntity(ID id);

  List<T> getEntities();

  T createEntity(T entity);

  T updateEntity(T entity, ID id);

  void deleteEntity(ID id);
}
