package com.dgsApp.poc.mappers;


import com.dgsApp.poc.apiLayer.inputs.CreateUserInput;
import com.dgsApp.poc.serviceLayer.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CreateUserInputToUserDtoMapper {
    CreateUserInput userDtoToCreateUserInput(UserDto userDto);
    @Mapping(target = "id", ignore = true)
    UserDto createUserInputToUserDto(CreateUserInput createUserInput);
}
