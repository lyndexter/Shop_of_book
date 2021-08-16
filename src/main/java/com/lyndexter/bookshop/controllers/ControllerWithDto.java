package com.lyndexter.bookshop.controllers;

import java.util.List;

public interface ControllerWithDto<T, S> {
  T createDto(S entity);

  List<T> createDtos(Iterable<S> entities);
}
