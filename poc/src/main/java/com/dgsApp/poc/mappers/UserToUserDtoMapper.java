package com.dgsApp.poc.mappers;

import com.dgsApp.poc.dataLayer.entities.User;
import com.dgsApp.poc.serviceLayer.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserToUserDtoMapper {
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
}
