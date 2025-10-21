package com.example.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TodoRequestDTO {
  @NotBlank(message = "Todo Name Cannot Be Empty")
  private String todoName;
  @NotNull(message = "Todo Status Must Be Provided")
  private boolean completed;
  @NotNull(message = "User ID Must Be Provided")
  private Long userId;

}
