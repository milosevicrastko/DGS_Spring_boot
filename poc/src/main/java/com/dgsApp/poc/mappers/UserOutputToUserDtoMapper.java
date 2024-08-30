package com.dgsApp.poc.mappers;

import com.dgsApp.poc.apiLayer.outputs.UserOutput;
import com.dgsApp.poc.serviceLayer.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserOutputToUserDtoMapper {

    UserOutput userDtoToUserOutput(UserDto userDto);
    UserDto userOutputToUserDto(UserOutput userOutput);
}
