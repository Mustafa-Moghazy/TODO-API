package com.example.todo.dto;

import jakarta.validation.constraints.*;

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
  @NotBlank(message = "Role Is Required")
  @Pattern(regexp = "ADMIN|USER", message = "Role must be one of ADMIN or USER")
  private String role;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}
