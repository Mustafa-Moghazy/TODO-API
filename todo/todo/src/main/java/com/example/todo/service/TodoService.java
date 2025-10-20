package com.example.todo.service;

import com.example.todo.dto.TodoRequestDTO;
import com.example.todo.dto.TodoResponseDTO;
import com.example.todo.entity.ToDo;

import java.util.List;

public interface TodoService {
  ToDo createToDo(TodoRequestDTO todo);
  List<TodoResponseDTO> getAllTodos();
  List<TodoResponseDTO> getTodosByUserId(Long userId);
  ToDo getTodoByID(Long id);
  ToDo updateTodo(Long id,TodoRequestDTO toDo);
  void deleteTodo(Long id);

}
