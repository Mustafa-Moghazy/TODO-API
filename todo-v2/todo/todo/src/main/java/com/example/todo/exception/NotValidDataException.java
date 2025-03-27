package com.example.todo.exception;

public class NotValidDataException extends RuntimeException {
  public NotValidDataException(String s) {
    super(s);
  }
}
