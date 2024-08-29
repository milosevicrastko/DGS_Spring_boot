package com.dgsApp.poc.apiLayer;

import com.dgsApp.poc.serviceLayer.dto.UserDto;
import com.dgsApp.poc.serviceLayer.services.interfaces.UserService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import lombok.RequiredArgsConstructor;

import java.util.List;


@DgsComponent
@RequiredArgsConstructor
public class UserDataFetcher {

    private UserService userService;

    @DgsQuery(field = "user")
    public List<UserDto> getAllUsers(){
        return userService.findAll();
    }


}
