package com.dgs.poc.mappers;

import com.dgs.poc.apiLayer.inputs.UpdateUserInput;
import com.dgs.poc.serviceLayer.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UpdateUserInputToUserDtoMapper {
    UpdateUserInput userDtoToUpdateUserInput(UserDto userDto);
    @Mapping(target = "id", ignore = true)
    UserDto updateUserInputToUserDto(UpdateUserInput updateUserInput);
}
