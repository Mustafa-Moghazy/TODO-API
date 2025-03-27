package com.example.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(RecordNotFoundException.class)
  public ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request){
    ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NotValidDataException.class)
  public ResponseEntity<Object> handleNotValidDataException(NotValidDataException ex, WebRequest request){
    ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NotValidRoleException.class)
  public ResponseEntity<Object> handleNotValidRoleException(NotValidRoleException ex, WebRequest request){
    ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UserNameIsAlreadyExistException.class)
  public ResponseEntity<Object> handleUserNameIsAlreadyExistException(UserNameIsAlreadyExistException ex, WebRequest request){
    ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
}
