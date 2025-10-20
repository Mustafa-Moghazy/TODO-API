package com.example.todo.mapper;

import com.example.todo.dto.UserRequestDTO;
import com.example.todo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
  User toUserEntity(UserRequestDTO userRequestDTO);
}
