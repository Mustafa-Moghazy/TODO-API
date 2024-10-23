package com.example.todo.service;

import com.example.todo.dto.TodoRequestDTO;
import com.example.todo.dto.TodoResponseDTO;
import com.example.todo.entity.ToDo;
import com.example.todo.entity.User;
import com.example.todo.exception.NotValidDataException;
import com.example.todo.exception.RecordNotFoundException;
import com.example.todo.mapper.TodoMapper;
import com.example.todo.repository.TodoRepository;
import com.example.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {
  @Autowired
  private TodoRepository todoRepo;
  @Autowired
  private UserService userService;
  @Autowired
  private TodoMapper todoMapper;

  @Override
  public ToDo createToDo(TodoRequestDTO todoRequestDTO) {
    User user = userService.findUserByID(todoRequestDTO.getUserId());
    ToDo newTodo = todoMapper.toEntity(todoRequestDTO);
    return todoRepo.save(newTodo);
  }

  @Override
  public List<TodoResponseDTO> getAllTodos() {
    return todoRepo.findAll().stream().map(toDo -> todoMapper.toTodoDTO(toDo)).collect(Collectors.toList());
  }

  @Override
  public List<ToDo> getTodosByUserId(Long userId) {
    User user = userService.findUserByID(userId);
    return todoRepo.findByUserId(user.getId());
  }

  @Override
  public ToDo getTodoByID(Long id) {
    Optional<ToDo> existingTodo = todoRepo.findById(id);
    if(existingTodo.isEmpty()){
      throw new RecordNotFoundException("Could not find Todo with Id : " + id);
    }
    return existingTodo.get();
  }

  @Override
  public ToDo updateTodo(Long id, TodoRequestDTO newToDo) {
    ToDo todo = getTodoByID(id);
    todo.setTodoName(newToDo.getTodoName());
    todo.setCompleted(newToDo.isCompleted());
    return todoRepo.save(todo);
  }

  @Override
  public void deleteTodo(Long id) {
    ToDo todo = getTodoByID(id);
    todoRepo.deleteById(id);
  }
}
