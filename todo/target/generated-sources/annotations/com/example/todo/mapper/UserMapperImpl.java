package com.example.todo.mapper;

import com.example.todo.dto.UserRequestDTO;
import com.example.todo.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-20T17:32:26+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUserEntity(UserRequestDTO userRequestDTO) {
        if ( userRequestDTO == null ) {
            return null;
        }

        User user = new User();

        return user;
    }
}
