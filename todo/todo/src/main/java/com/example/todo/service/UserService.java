package com.example.todo.service;

import com.example.todo.dto.TodoResponseDTO;
import com.example.todo.dto.UserRequestDTO;
import com.example.todo.dto.UserResponseDTO;
import com.example.todo.entity.User;

import java.util.List;

public interface UserService {
  UserResponseDTO createUser(UserRequestDTO user);
  User findUserByID(Long id);
  List<UserResponseDTO> getAllUsers();
  List<TodoResponseDTO> getUserTodos(Long userId);
  UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);
  void deleteUser(Long id);
  User findByUserName(String userName);
}
