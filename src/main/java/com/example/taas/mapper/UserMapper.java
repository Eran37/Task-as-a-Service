package com.example.taas.mapper;

import com.example.taas.beans.ClientType;
import com.example.taas.beans.User;
import com.example.taas.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<User, UserDto>{

    private final TaskMapper mapper;

    public User toDao(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .clientType(ClientType.valueOf(userDto.getType()))
                .tasks(mapper.toDaoList(userDto.getTasks()))
                .build();
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .type(user.getClientType().name())
                .tasks(mapper.toDtoList(user.getTasks()))
                .build();
    }

    public List<User> toDaoList(List<UserDto> dtoList) {
        return dtoList.stream().map(this::toDao).collect(Collectors.toList());
    }

    public List<UserDto> toDtoList(List<User> users) {
        return users.stream().map(this::toDto).collect(Collectors.toList());
    }
}
