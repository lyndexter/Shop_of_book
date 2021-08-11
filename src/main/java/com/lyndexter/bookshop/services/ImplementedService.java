package com.lyndexter.bookshop.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class ImplementedService<T, ID> implements DefaultService<T, ID> {

  protected abstract JpaRepository<T, ID> getRepository();

  protected abstract T changeEntity(T changingEntity, T entity);

  protected abstract void throwExeption();

  @Override
  public T getEntity(ID id) {
    if (getRepository().existsById(id)) {
      return getRepository().findById(id).get();
    }
    throwExeption();
    return null;
  }

  @Override
  public List<T> getEntities() {
    return getRepository().findAll();
  }

  @Override
  public T createEntity(T entity) {
    return getRepository().save(entity);
  }

  @Override
  public T updateEntity(T entity, ID id) {
    if (getRepository().existsById(id)) {
      T changingEntity = getRepository().getById(id);
      changingEntity = changeEntity(changingEntity, entity);
      return getRepository().save(changingEntity);
    }
    throwExeption();
    return null;
  }

  @Override
  public void deleteEntity(ID id) {
    if (getRepository().existsById(id)) {
      getRepository().deleteById(id);
      return;
    }
    throwExeption();
  }
}
