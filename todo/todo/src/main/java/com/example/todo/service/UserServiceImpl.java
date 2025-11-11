package com.example.todo.service;

import com.example.todo.dto.TodoResponseDTO;
import com.example.todo.dto.UserRequestDTO;
import com.example.todo.dto.UserResponseDTO;
import com.example.todo.entity.Role;
import com.example.todo.entity.ToDo;
import com.example.todo.entity.User;
import com.example.todo.exception.NotValidRoleException;
import com.example.todo.exception.RecordNotFoundException;
import com.example.todo.exception.UserNameIsAlreadyExistException;
import com.example.todo.mapper.TodoMapper;
import com.example.todo.mapper.UserMapper;
import com.example.todo.repository.RoleRepository;
import com.example.todo.repository.TodoRepository;
import com.example.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
  @Autowired
  private UserRepository userRepo;
  @Autowired
  private TodoRepository todoRepo;
  @Autowired
  private TodoMapper todoMapper;
  @Autowired
  RoleRepository roleRepo;
  @Autowired
  PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;

    @Override
  public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
    Optional<User> user = userRepo.findByUserName(userRequestDTO.getUserName());
    if(user.isPresent()){
      throw new UserNameIsAlreadyExistException("UserName Is Already Exist, Try Anther One");
    }
    Set<Role> roles = userRequestDTO.getRoles().stream()
            .map( roleName -> roleRepo.findByName(roleName)
                    .orElseThrow( ()-> new NotValidRoleException("Role Not Found : " + roleName)))
            .collect(Collectors.toSet());

    User newUser = new User();
    newUser.setUserName(userRequestDTO.getUserName());
    newUser.setEmail(userRequestDTO.getEmail());
    newUser.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
    newUser.setRoles(roles);

    User saved = userRepo.save(newUser);
    return  userMapper.toUserResponseDto(saved);
  }

  @Override
  public User findUserByID(Long id) {
    return userRepo.findById(id)
            .orElseThrow( () -> new RecordNotFoundException("User With Id : " + id + " Not Found"));
  }

  @Override
  public List<UserResponseDTO> getAllUsers() {
      List<UserResponseDTO> response = userRepo.findAll().stream().map( user -> userMapper.toUserResponseDto(user)).toList();
    return response;
  }

  @Override
  public List<TodoResponseDTO> getUserTodos(Long userId) {
    List<ToDo> todos = todoRepo.getToDosByUserId(userId);
    return todos.stream().map(todoMapper::toTodoDTO).collect(Collectors.toList());
  }

  @Override
  public UserResponseDTO updateUser(Long userId, UserRequestDTO updatedUser) {
    User currentUser = findUserByID(userId);
    currentUser.setUserName(updatedUser.getUserName());
    currentUser.setEmail(updatedUser.getEmail());
    currentUser.setPassword( passwordEncoder.encode( updatedUser.getPassword() ) );
    // Check if the Role is found //
    Set<Role> roles = updatedUser.getRoles().stream()
            .map(roleName -> roleRepo.findByName(roleName)
                    .orElseThrow(()-> new RuntimeException("Role Not Found")))
            .collect(Collectors.toSet());
    currentUser.setRoles(roles);

    User saved = userRepo.save(currentUser);
    return userMapper.toUserResponseDto(saved);
  }

  @Override
  public void deleteUser(Long userId) {
    User currentUser = userRepo.findById(userId).orElseThrow(()-> new RecordNotFoundException("User With ID: "+ userId + " Not Found!"));
    userRepo.delete(currentUser);
  }

  @Override
  public UserResponseDTO findByUserName(String userName) {
        User user = userRepo.findByUserName(userName.trim())
                .orElseThrow(()-> new RecordNotFoundException("User With Name: "+ userName + " Not Found!"));
    return userMapper.toUserResponseDto(user);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepo.findByUserName(username).orElseThrow(()-> new RecordNotFoundException("User Not Found!!"));

    List<SimpleGrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList();

    return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities );
  }
}
