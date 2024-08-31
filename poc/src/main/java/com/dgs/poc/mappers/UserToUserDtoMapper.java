package com.dgs.poc.mappers;

import com.dgs.poc.dataLayer.entities.User;
import com.dgs.poc.serviceLayer.dto.UserDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserToUserDtoMapper {
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
}
