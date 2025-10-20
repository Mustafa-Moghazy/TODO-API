package com.example.todo.exception;

public class UserNameIsAlreadyExistException extends RuntimeException {
  public UserNameIsAlreadyExistException(String s) {
    super(s);
  }
}
