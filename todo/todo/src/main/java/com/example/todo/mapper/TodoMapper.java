package com.example.todo.mapper;

import com.example.todo.dto.TodoRequestDTO;
import com.example.todo.dto.TodoResponseDTO;
import com.example.todo.entity.ToDo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring" )
public interface TodoMapper {
    @Mapping(source = "user.id", target = "userId")
    TodoResponseDTO toTodoDTO(ToDo toDo);

    @Mapping(source = "userId", target = "user.id")
    ToDo toEntity(TodoRequestDTO toDo);

}
