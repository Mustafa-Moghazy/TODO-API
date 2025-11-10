package com.example.todo.mapper;

import com.example.todo.dto.TodoResponseDTO;
import com.example.todo.dto.UserResponseDTO;
import com.example.todo.entity.Role;
import com.example.todo.entity.ToDo;
import com.example.todo.entity.User;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-10T16:22:53+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private TodoMapper todoMapper;

    @Override
    public UserResponseDTO toUserResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setTodos( toDoListToTodoResponseDTOList( user.getTodos() ) );
        userResponseDTO.setId( user.getId() );
        userResponseDTO.setUserName( user.getUserName() );
        userResponseDTO.setEmail( user.getEmail() );
        Set<Role> set = user.getRoles();
        if ( set != null ) {
            userResponseDTO.setRoles( new LinkedHashSet<Role>( set ) );
        }

        return userResponseDTO;
    }

    protected List<TodoResponseDTO> toDoListToTodoResponseDTOList(List<ToDo> list) {
        if ( list == null ) {
            return null;
        }

        List<TodoResponseDTO> list1 = new ArrayList<TodoResponseDTO>( list.size() );
        for ( ToDo toDo : list ) {
            list1.add( todoMapper.toTodoDTO( toDo ) );
        }

        return list1;
    }
}
