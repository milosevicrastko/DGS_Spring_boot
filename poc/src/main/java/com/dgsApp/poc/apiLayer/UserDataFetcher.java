package com.dgsApp.poc.apiLayer;

import com.dgsApp.poc.apiLayer.inputs.CreateUserInput;
import com.dgsApp.poc.apiLayer.inputs.UpdateUserInput;
import com.dgsApp.poc.apiLayer.outputs.UserOutput;
import com.dgsApp.poc.mappers.*;
import com.dgsApp.poc.serviceLayer.dto.UserDto;
import com.dgsApp.poc.serviceLayer.services.interfaces.UserService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@DgsComponent
@RequiredArgsConstructor
public class UserDataFetcher {

    private final UserService userService;
    private final CreateUserInputToUserDtoMapper createUserInputToUserDtoMapper = new CreateUserInputToUserDtoMapperImpl();
    private final UpdateUserInputToUserDtoMapper updateUserInputToUserDtoMapper = new UpdateUserInputToUserDtoMapperImpl();
    private final UserOutputToUserDtoMapper userOutputToUserDtoMapper = new UserOutputToUserDtoMapperImpl();

    @DgsQuery(field = "user")
    public UserDto getUserById(@InputArgument Long id) {
        return userService.findById(id);
    }

    @DgsQuery(field = "users")
    public List<UserOutput> getAllUsers() {
        return userService.findAll().stream().map(userOutputToUserDtoMapper::userDtoToUserOutput).toList();
    }

    @DgsMutation(field = "createUser")
    public UserOutput createUser(@InputArgument CreateUserInput input) {
        UserDto userDto = userService.save(createUserInputToUserDtoMapper.createUserInputToUserDto(input));
        return userOutputToUserDtoMapper.userDtoToUserOutput(userDto);
    }

    @DgsMutation(field = "updateUser")
    public UserDto updateUser(@InputArgument Long id, @InputArgument UpdateUserInput input) {
        return userService.update(id, updateUserInputToUserDtoMapper.updateUserInputToUserDto(input));
    }

    @DgsMutation(field = "deleteUser")
    public UserDto deleteUser(@InputArgument Long id) {
        return userService.deleteById(id);
    }

}
