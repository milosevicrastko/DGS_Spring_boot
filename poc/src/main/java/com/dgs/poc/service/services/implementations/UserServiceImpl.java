package com.dgs.poc.service.services.implementations;

import com.dgs.poc.data.entities.User;
import com.dgs.poc.data.repositories.UserRepository;
import com.dgs.poc.exceptons.custom.UserNotFoundException;
import com.dgs.poc.mappers.UserToUserDtoMapper;
import com.dgs.poc.service.dto.UserDto;
import com.dgs.poc.service.services.interfaces.UserService;
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
        UserDto exisingUserDto = userToUserDto.userToUserDto(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
        if (userDto.getName() != null) {
            exisingUserDto.setName(userDto.getName());
        }
        if (userDto.getEmail() != null) {
            exisingUserDto.setEmail(userDto.getEmail());
        }
        return userToUserDto.userToUserDto(userRepository.save(userToUserDto.userDtoToUser(exisingUserDto)));

    }

    @Override
    public UserDto deleteById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);
        return userToUserDto.userToUserDto(user);
    }
}
