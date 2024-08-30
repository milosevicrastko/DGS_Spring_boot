package com.dgsApp.poc.serviceLayer.services.implementations;

import com.dgsApp.poc.dataLayer.entities.User;
import com.dgsApp.poc.dataLayer.repositories.UserRepository;
import com.dgsApp.poc.exceptons.pocExceptions.UserNotFoundException;
import com.dgsApp.poc.mappers.UserToUserDtoMapper;
import com.dgsApp.poc.mappers.UserToUserDtoMapperImpl;
import com.dgsApp.poc.serviceLayer.dto.UserDto;
import com.dgsApp.poc.serviceLayer.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserToUserDtoMapper userToUserDto;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userToUserDto::userToUserDto).toList();
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return userToUserDto.userToUserDto(user);
    }

    @Override
    public UserDto save(UserDto userDto) {
       User user = userRepository.save(userToUserDto.userDtoToUser(userDto));
       return userToUserDto.userToUserDto(user);
    }

    @Override
    public UserDto update(Long id, UserDto userDto) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        return userToUserDto.userToUserDto(userRepository.save(userToUserDto.userDtoToUser(userDto)));

    }

    @Override
    public UserDto deleteById(Long id) {
        UserDto userDto = findById(id);
        userRepository.deleteById(id);
        return userDto;
    }
}
