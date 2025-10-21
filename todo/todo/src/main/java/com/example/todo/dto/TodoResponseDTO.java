package com.example.todo.dto;

import lombok.Data;

@Data
public class TodoResponseDTO {
  private Long id;
  private String todoName;
  private boolean completed;
  private Long userId;

}
