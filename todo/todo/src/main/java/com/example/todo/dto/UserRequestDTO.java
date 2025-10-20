package com.example.todo.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Set;

@Data
public class UserRequestDTO {
  @NotBlank(message = "User Name Cannot Be Empty")
  @Size(min = 3, message = "User Name Cannot be lees than 3 char")
  private String userName;
  @Email(message = "Not valid email address provided")
  @NotBlank(message = "Email Is Required")
  private String email;
  @NotBlank(message = "Password Is Required")
  @Size(min = 6, message = "Password Cannot be lees than 6 char")
  private String password;
  @NotEmpty(message = "At Least One Role Is Required")
  private Set<String> roles;

}
