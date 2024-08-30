package com.dgsApp.poc.mappers;

import com.dgsApp.poc.apiLayer.inputs.UpdateUserInput;
import com.dgsApp.poc.serviceLayer.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UpdateUserInputToUserDtoMapper {
    UpdateUserInput userDtoToUpdateUserInput(UserDto userDto);
    @Mapping(target = "id", ignore = true)
    UserDto updateUserInputToUserDto(UpdateUserInput updateUserInput);
}
