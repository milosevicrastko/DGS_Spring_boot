package com.dgs.poc.mappers;

import com.dgs.poc.apiLayer.outputs.UserOutput;
import com.dgs.poc.serviceLayer.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserOutputToUserDtoMapper {

    UserOutput userDtoToUserOutput(UserDto userDto);
    UserDto userOutputToUserDto(UserOutput userOutput);
}
