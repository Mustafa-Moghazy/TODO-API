package com.example.todo.service;

import com.example.todo.dto.TodoResponseDTO;
import com.example.todo.dto.UserRequestDTO;
import com.example.todo.entity.ToDo;
import com.example.todo.entity.User;
import com.example.todo.exception.NotValidRoleException;
import com.example.todo.exception.RecordNotFoundException;
import com.example.todo.mapper.TodoMapper;
import com.example.todo.mapper.UserMapper;
import com.example.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
  @Autowired
  private UserRepository userRepo;
  @Autowired
  private TodoMapper todoMapper;
  @Autowired
  private UserMapper userMapper;
  @Override
  public User createUser(UserRequestDTO userRequestDTO) {
    return userRepo.save(userMapper.toUserEntity(userRequestDTO));
  }

  @Override
  public User findUserByID(Long id) {
    Optional<User> existingUser = userRepo.findById(id);
    if(existingUser.isEmpty()){
      throw new RecordNotFoundException("Couldn't Find User With Id : " + id);
    }
    return existingUser.get();
  }

  @Override
  public List<User> getAllUsers() {
    return userRepo.findAll();
  }

  @Override
  public List<TodoResponseDTO> getUserTodos(Long userId) {
    User existingUser = findUserByID(userId);
    List<ToDo> todos = existingUser.getToDos();
    return todos.stream().map(todoMapper::toTodoDTO).collect(Collectors.toList());
  }

  @Override
  public User updateUser(Long userId, UserRequestDTO updatedUser) {
    User currentUser = findUserByID(userId);
    currentUser.setUserName(updatedUser.getUserName());
    currentUser.setEmail(updatedUser.getEmail());
    currentUser.setPassword(updatedUser.getPassword());
    currentUser.setRole(updatedUser.getRole());
    return userRepo.save(currentUser);
  }

  @Override
  public void deleteUser(Long userId) {
    User currentUser = findUserByID(userId);
    userRepo.deleteById(userId);
  }
}
