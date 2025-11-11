package com.example.todo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequestDTO {
    @NotBlank(message = "User Name Is Required")
    private String userName;
    @NotBlank(message = "Password Is Required")
    private String password;

}
