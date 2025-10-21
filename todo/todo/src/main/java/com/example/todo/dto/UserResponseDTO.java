package com.example.todo.dto;

import com.example.todo.entity.Role;
import com.example.todo.dto.TodoResponseDTO;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UserResponseDTO {
    private Long id;
    private String userName;
    private String email;
    private Set<Role> roles;
    private List<TodoResponseDTO> todos;

}
