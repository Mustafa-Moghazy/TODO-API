package com.example.todo.controller;

import com.example.todo.dto.TodoRequestDTO;
import com.example.todo.dto.TodoResponseDTO;
import com.example.todo.entity.ToDo;
import com.example.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
public class TodoController {
  @Autowired
  private TodoService todoService;

  @GetMapping()
  public List<TodoResponseDTO> getAllTodos(){
    return todoService.getAllTodos();
  }

  @GetMapping("/{todoId}")
  public ToDo getTodoById(@PathVariable Long todoId){
    return todoService.getTodoByID(todoId);
  }

  @GetMapping("user/{userId}")
  public List<TodoResponseDTO> getTodosByUserId(@PathVariable Long userId){
    return todoService.getTodosByUserId(userId);
  }

  @PostMapping()
  public ToDo createTodo( @Valid @RequestBody TodoRequestDTO todo){
    return todoService.createToDo(todo);
  }

  @PutMapping("/{todoId}")
  public ToDo updateTodo(@PathVariable Long todoId, @Valid @RequestBody TodoRequestDTO newTodo){
    return todoService.updateTodo(todoId, newTodo);
  }

  @DeleteMapping("/{todoId}")
  public void deleteTodo(@PathVariable Long todoId){
    todoService.deleteTodo(todoId);
  }
}
