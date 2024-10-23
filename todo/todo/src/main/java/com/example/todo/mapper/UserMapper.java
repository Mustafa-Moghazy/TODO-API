package com.example.todo.mapper;

import com.example.todo.dto.UserRequestDTO;
import com.example.todo.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  User toUserEntity(UserRequestDTO userRequestDTO);
}
