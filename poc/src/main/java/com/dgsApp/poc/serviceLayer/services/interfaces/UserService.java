package com.dgsApp.poc.serviceLayer.services.interfaces;

import com.dgsApp.poc.serviceLayer.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> findAll();
    UserDto findById(Long id);
    void save(UserDto userDto);
    void deleteById(Long id);
}
