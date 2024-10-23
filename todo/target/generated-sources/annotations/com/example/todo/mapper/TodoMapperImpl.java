package com.example.todo.mapper;

import com.example.todo.dto.TodoRequestDTO;
import com.example.todo.dto.TodoResponseDTO;
import com.example.todo.entity.ToDo;
import com.example.todo.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-23T01:14:28+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class TodoMapperImpl implements TodoMapper {

    @Override
    public TodoResponseDTO toTodoDTO(ToDo toDo) {
        if ( toDo == null ) {
            return null;
        }

        TodoResponseDTO todoResponseDTO = new TodoResponseDTO();

        todoResponseDTO.setUserId( toDoUserId( toDo ) );
        todoResponseDTO.setId( toDo.getId() );
        todoResponseDTO.setTodoName( toDo.getTodoName() );
        todoResponseDTO.setCompleted( toDo.isCompleted() );

        return todoResponseDTO;
    }

    @Override
    public ToDo toEntity(TodoRequestDTO toDo) {
        if ( toDo == null ) {
            return null;
        }

        ToDo toDo1 = new ToDo();

        toDo1.setUser( todoRequestDTOToUser( toDo ) );
        toDo1.setTodoName( toDo.getTodoName() );
        toDo1.setCompleted( toDo.isCompleted() );

        return toDo1;
    }

    private Long toDoUserId(ToDo toDo) {
        if ( toDo == null ) {
            return null;
        }
        User user = toDo.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected User todoRequestDTOToUser(TodoRequestDTO todoRequestDTO) {
        if ( todoRequestDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( todoRequestDTO.getUserId() );

        return user;
    }
}
