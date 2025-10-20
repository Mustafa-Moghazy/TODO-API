package com.example.todo.controller;

import com.example.todo.dto.TodoResponseDTO;
import com.example.todo.dto.UserRequestDTO;
import com.example.todo.entity.ToDo;
import com.example.todo.entity.User;
import com.example.todo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/admin/users")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("")
  public List<User> getAllUsers(){
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  public User getUserById(@PathVariable Long id){
    return userService.findUserByID(id);
  }

  @GetMapping("/{userId}/todos")
  public List<TodoResponseDTO> getUserTodos(@PathVariable Long userId){
    return userService.getUserTodos(userId);
  }

  @PostMapping("")
  public User createUser(@Valid @RequestBody UserRequestDTO newUser){
    return userService.createUser(newUser);
  }

  @PutMapping("/{userId}")
  public User updateUser(@PathVariable Long userId, @Valid @RequestBody UserRequestDTO newUser){
    return userService.updateUser(userId, newUser);
  }

  @DeleteMapping("/{userId}")
  public void deleteUser (@PathVariable Long userId){
    userService.deleteUser(userId);
  }
}
