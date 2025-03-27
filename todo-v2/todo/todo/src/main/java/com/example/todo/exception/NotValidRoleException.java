package com.example.todo.exception;

public class NotValidRoleException extends RuntimeException {
  public NotValidRoleException(String s) {
    super(s);
  }
}
