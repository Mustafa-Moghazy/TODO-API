package com.example.todo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "todos")
public class ToDo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String todoName;
  private boolean completed;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
