package com.example.todo.dto;

public class AuthResponseDTO {
    private String token;
    private UserResponseDTO userResponseDTO;

    public AuthResponseDTO(String token, UserResponseDTO userResponseDTO) {
        this.token = token;
        this.userResponseDTO = userResponseDTO;
    }

    public String getToken() {
        return token;
    }

    public UserResponseDTO getUserResponseDTO() {
        return userResponseDTO;
    }
}
