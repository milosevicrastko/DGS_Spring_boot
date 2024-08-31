package com.dgs.poc.mappers;


import com.dgs.poc.apiLayer.inputs.CreateUserInput;
import com.dgs.poc.serviceLayer.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateUserInputToUserDtoMapper {
    CreateUserInput userDtoToCreateUserInput(UserDto userDto);
    @Mapping(target = "id", ignore = true)
    UserDto createUserInputToUserDto(CreateUserInput createUserInput);
}
