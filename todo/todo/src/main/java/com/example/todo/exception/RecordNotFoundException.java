package com.example.todo.exception;

public class RecordNotFoundException extends RuntimeException{
  public RecordNotFoundException(String message){
    super(message);
  }
}
