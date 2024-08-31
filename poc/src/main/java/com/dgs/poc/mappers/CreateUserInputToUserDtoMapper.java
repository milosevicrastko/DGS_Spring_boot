package com.dgs.poc.mappers;


import com.dgs.poc.api.inputs.CreateUserInput;
import com.dgs.poc.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateUserInputToUserDtoMapper {

    @Mapping(target = "id", ignore = true)
    UserDto createUserInputToUserDto(CreateUserInput createUserInput);
}
