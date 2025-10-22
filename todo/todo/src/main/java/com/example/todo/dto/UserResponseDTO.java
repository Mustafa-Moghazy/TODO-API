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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<TodoResponseDTO> getTodos() {
        return todos;
    }

    public void setTodos(List<TodoResponseDTO> todos) {
        this.todos = todos;
    }
}
