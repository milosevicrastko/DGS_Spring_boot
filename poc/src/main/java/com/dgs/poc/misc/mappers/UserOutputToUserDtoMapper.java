package com.dgs.poc.misc.mappers;

import com.dgs.poc.api.outputs.UserOutput;
import com.dgs.poc.service.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserOutputToUserDtoMapper {

    UserOutput userDtoToUserOutput(UserDto userDto);
}
