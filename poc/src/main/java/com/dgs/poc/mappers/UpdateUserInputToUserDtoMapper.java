package com.dgs.poc.mappers;

import com.dgs.poc.api.inputs.UpdateUserInput;
import com.dgs.poc.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UpdateUserInputToUserDtoMapper {

    @Mapping(target = "id", ignore = true)
    UserDto updateUserInputToUserDto(UpdateUserInput updateUserInput);
}
