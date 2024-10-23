package com.example.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TodoRequestDTO {
  @NotBlank(message = "Todo Name Cannot Be Empty")
  private String todoName;
  @NotNull(message = "Todo Status Must Be Provided")
  private boolean completed;
  @NotNull(message = "User ID Must Be Provided")
  private Long userId;

  public String getTodoName() {
    return todoName;
  }

  public void setTodoName(String todoName) {
    this.todoName = todoName;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }
}
