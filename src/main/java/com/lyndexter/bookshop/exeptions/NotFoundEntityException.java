package com.lyndexter.bookshop.exeptions;

public class NotFoundEntityException extends RuntimeException {
  public NotFoundEntityException(String message) {
    super(message);
  }
}
