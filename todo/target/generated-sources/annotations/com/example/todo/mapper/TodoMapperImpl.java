package com.example.todo.mapper;

import com.example.todo.dto.TodoRequestDTO;
import com.example.todo.dto.TodoResponseDTO;
import com.example.todo.entity.ToDo;
import com.example.todo.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-10T16:22:52+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25 (Oracle Corporation)"
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

        toDo1.setTodoName( toDo.getTodoName() );
        toDo1.setCompleted( toDo.isCompleted() );

        return toDo1;
    }

    private Long toDoUserId(ToDo toDo) {
        User user = toDo.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }
}
