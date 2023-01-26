package com.example.taas.beans;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapping {
    @Mapping(source = "identifier", target = "id")
    @Mapping(source = "mailAddress", target = "email")
    @Mapping(source = "keyword", target = "password")
    @Mapping(source = "genre", target = "type")
    @Mapping(source = "todos", target = "tasks")
    User userDtoYesToUser(UserDtoYes userDtoYes);

    @InheritInverseConfiguration(name = "userDtoYesToUser")
    UserDtoYes userToUserDtoYes(User user);

    @InheritConfiguration(name = "userDtoYesToUser")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateUserFromUserDtoYes(UserDtoYes userDtoYes, @MappingTarget User user);

    @AfterMapping
    default void linkTasks(@MappingTarget User user) {
        user.getTasks().forEach(task -> task.setUser(user));
    }
}
