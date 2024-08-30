package com.dgsApp.poc.apiLayer;

import com.dgsApp.poc.serviceLayer.dto.UserDto;
import com.dgsApp.poc.serviceLayer.services.interfaces.UserService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.RequiredArgsConstructor;

import java.util.List;


@DgsComponent
@RequiredArgsConstructor
public class UserDataFetcher {

    private final UserService userService;

    @DgsQuery(field = "user")
    public UserDto getUserById(@InputArgument Long id) {
        return userService.findById(id);
    }


    @DgsQuery(field = "users")
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    @DgsMutation(field = "createUser")
    public UserDto createUser(@InputArgument String name, @InputArgument String email) {
        return userService.save(new UserDto(null, name, email));
    }

    @DgsMutation(field = "updateUser")
    public UserDto updateUser(@InputArgument Long id, @InputArgument String name, @InputArgument String email) {
        return userService.update(id, new UserDto(id, name, email));
    }

    @DgsMutation(field = "deleteUser")
    public void deleteUser(@InputArgument Long id) {
        userService.deleteById(id);
    }

}
