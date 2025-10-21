package com.example.todo.mapper;

import com.example.todo.dto.UserResponseDTO;
import com.example.todo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Maps User entity <-> UserResponseDTO.
 * Automatically handles roles and todos mapping using ToDoMapper.
 */
@Mapper(componentModel = "spring", uses = {TodoMapper.class})
public interface UserMapper {


    // Entity -> DTO
    @Mapping(target = "todos", source = "todos")
    //@Mapping(target = "password", ignore = true)
    UserResponseDTO toUserResponseDto(User user);
}
