package com.dgs.poc.serviceLayer.services.interfaces;

import com.dgs.poc.serviceLayer.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    UserDto findById(Long id);
    UserDto save(UserDto userDto);
    UserDto update(Long id, UserDto userDto);
    UserDto deleteById(Long id);
}
