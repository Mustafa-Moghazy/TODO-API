package com.example.todo.service;

import com.example.todo.dto.TodoResponseDTO;
import com.example.todo.dto.UserRequestDTO;
import com.example.todo.entity.Role;
import com.example.todo.entity.ToDo;
import com.example.todo.entity.User;
import com.example.todo.exception.RecordNotFoundException;
import com.example.todo.exception.UserNameIsAlreadyExistException;
import com.example.todo.mapper.TodoMapper;
import com.example.todo.mapper.UserMapper;
import com.example.todo.repository.RoleRepository;
import com.example.todo.repository.TodoRepository;
import com.example.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
  @Autowired
  private UserRepository userRepo;
  @Autowired
  private TodoRepository todoRepo;
  @Autowired
  private TodoMapper todoMapper;
  @Autowired
  RoleRepository roleRepo;
  @Override
  public User createUser(UserRequestDTO userRequestDTO) {
    Optional<User> user = userRepo.findByUserName(userRequestDTO.getUserName());
    if(user.isPresent()){
      throw new UserNameIsAlreadyExistException("UserName Is Already Exist, Try Anther One");
    }
    Set<Role> roles = userRequestDTO.getRoles().stream()
      .map( roleName -> roleRepo.findByName(roleName)
        .orElseThrow( ()-> new RuntimeException("Role Not Found : " + roleName)))
      .collect(Collectors.toSet());

    User newUser = new User();
    newUser.setUserName(userRequestDTO.getUserName());
    newUser.setEmail(userRequestDTO.getEmail());
    newUser.setPassword(userRequestDTO.getPassword());
    newUser.setRoles(roles);

    return userRepo.save(newUser);
  }

  @Override
  public User findUserByID(Long id) {
    return userRepo.findById(id)
      .orElseThrow( () -> new RecordNotFoundException("User With Id : " + id + " Not Found"));
  }

  @Override
  public List<User> getAllUsers() {
    return userRepo.findAll();
  }

  @Override
  public List<TodoResponseDTO> getUserTodos(Long userId) {
    List<ToDo> todos = todoRepo.getToDosByUserId(userId);
    return todos.stream().map(todoMapper::toTodoDTO).collect(Collectors.toList());
  }

  @Override
  public User updateUser(Long userId, UserRequestDTO updatedUser) {
    User currentUser = findUserByID(userId);
    currentUser.setUserName(updatedUser.getUserName());
    currentUser.setEmail(updatedUser.getEmail());
    currentUser.setPassword(updatedUser.getPassword());
    // Check if the Role is found //
    Set<Role> roles = updatedUser.getRoles().stream()
      .map(roleName -> roleRepo.findByName(roleName)
        .orElseThrow(()-> new RuntimeException("Role Not Found")))
      .collect(Collectors.toSet());
    currentUser.setRoles(roles);
    return userRepo.save(currentUser);
  }

  @Override
  public void deleteUser(Long userId) {
    User currentUser = userRepo.findById(userId).orElseThrow(()-> new RecordNotFoundException("User With ID: "+ userId + " Not Found!"));
    userRepo.delete(currentUser);
  }

  @Override
  public User findByUserName(String userName) {
    return userRepo.findByUserName(userName.trim())
      .orElseThrow(()-> new RecordNotFoundException("User With Name: "+ userName + " Not Found!"));
  }

}
