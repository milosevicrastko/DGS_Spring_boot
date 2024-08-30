package com.dgsApp.poc.unitTests;

import com.dgsApp.poc.dataLayer.entities.User;
import com.dgsApp.poc.dataLayer.repositories.UserRepository;
import com.dgsApp.poc.mappers.UserToUserDtoMapper;
import com.dgsApp.poc.mappers.UserToUserDtoMapperImpl;
import com.dgsApp.poc.serviceLayer.dto.UserDto;
import com.dgsApp.poc.serviceLayer.services.implementations.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    private final UserToUserDtoMapper userToUserDtoMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private UserDto userDto;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(UserServiceTests.class);
        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName("John Doe");
        userDto.setEmail("john.doe@example.com");
    }


    @Test
    void testSaveUser() {
        User user = userToUserDtoMapper.userDtoToUser(userDto);
        when(userRepository.save(any(User.class))).thenReturn(user);
        UserDto savedUserDto = userService.save(userDto);
        assertNotNull(savedUserDto);
        assertEquals("John Doe", savedUserDto.getName());
        verify(userRepository, times(1)).
                save(userToUserDtoMapper.userDtoToUser(userDto));

    }

    @Test
    void testUpdateUser() {
        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.save(any(User.class))).thenReturn(userToUserDtoMapper.userDtoToUser(userDto));

        UserDto updatedUser = userService.update(1L, userDto);
        assertNotNull(updatedUser);
        assertEquals("John Done", updatedUser.getName());
        verify(userRepository, times(1)).save(userToUserDtoMapper.userDtoToUser(userDto));

    }

    @Test
    void testUpdateUserNotFound() {
        when(userRepository.existsById(1L)).thenReturn(false);
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            userService.update(1L, userDto);
        });

        assertEquals("User not found with id: 1", thrown.getMessage());
    }


    @Test
    void testDeleteUser() {
        userService.deleteById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteUserNotFound() {
        when(userRepository.existsById(1L)).thenReturn(false);
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            userService.deleteById(1L);
        });

        assertEquals("User not found with id: 1", thrown.getMessage());
    }


    @Test
    void testGetUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(userToUserDtoMapper.userDtoToUser(userDto)));
        UserDto foundUser = userService.findById(1L);
        assertNotNull(foundUser);
        assertEquals("John Doe", foundUser.getName());
        verify(userRepository, times(1)).findById(1L);
    }


    @Test
    void testGetAllUsers() {
        List<UserDto> users = Collections.singletonList(userDto);
        when(userRepository.findAll()).thenReturn(users.stream().map(userToUserDtoMapper::userDtoToUser).toList());

        List<UserDto> foundUsers = userService.findAll();

        assertNotNull(foundUsers);
        assertEquals(1, foundUsers.size());
        assertEquals("John Doe", foundUsers.getFirst().getName());
        verify(userRepository, times(1)).findAll();

    }
}
