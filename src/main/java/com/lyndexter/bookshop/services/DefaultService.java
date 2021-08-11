package com.lyndexter.bookshop.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DefaultService<T, ID> {

  public T getEntity(ID id);

  public List<T> getEntities();

  public T createEntity(T entity);

  public T updateEntity(T entity, ID id);

  public void deleteEntity(ID id);
}
