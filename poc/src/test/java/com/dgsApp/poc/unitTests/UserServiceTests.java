package com.dgsApp.poc.unitTests;

import com.dgsApp.poc.dataLayer.entities.User;
import com.dgsApp.poc.dataLayer.repositories.UserRepository;
import com.dgsApp.poc.mappers.UserToUserDtoMapper;
import com.dgsApp.poc.mappers.UserToUserDtoMapperImpl;
import com.dgsApp.poc.serviceLayer.dto.UserDto;
import com.dgsApp.poc.serviceLayer.services.implementations.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Spy
    private UserRepository userRepository;


    @Spy
    private final UserToUserDtoMapper userToUserDtoMapper = new UserToUserDtoMapperImpl();

    @InjectMocks
    private UserServiceImpl userService;

    private UserDto userDto;

    private AutoCloseable session;

    @BeforeEach
    public void setUp() {
        session = MockitoAnnotations.openMocks(this);
        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName("John Doe");
        userDto.setEmail("john.doe@example.com");
    }


    @Test
    void testSaveUser() {
        User user = userToUserDtoMapper.userDtoToUser(userDto);
        when(userRepository.save(any())).thenReturn(user);
        UserDto savedUserDto = userService.save(userDto);
        assertNotNull(savedUserDto);
        assertEquals("John Doe", savedUserDto.getName());
        verify(userRepository, times(1)).
                save(userToUserDtoMapper.userDtoToUser(userDto));

    }

    @Test
    void testUpdateUser() {
        User user = userToUserDtoMapper.userDtoToUser(userDto);
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(any())).thenReturn(user);
        UserDto updatedUser = userService.update(1L, userDto);
        assertNotNull(updatedUser);
        assertEquals("John Doe", updatedUser.getName());
        verify(userRepository, times(1)).save(userToUserDtoMapper.userDtoToUser(userDto));

    }

    @Test
    void testUpdateUserNotFound() {
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            userService.update(1L, userDto);
        });

        assertEquals("User with id 1 was not found", thrown.getMessage());
    }


    @Test
    void testDeleteUser() {
        User user = userToUserDtoMapper.userDtoToUser(userDto);
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        userService.deleteById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteUserNotFound() {
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            userService.deleteById(1L);
        });

        assertEquals("User with id 1 was not found", thrown.getMessage());
    }


    @Test
    void testGetUserById() {
        User user = userToUserDtoMapper.userDtoToUser(userDto);
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        UserDto foundUser = userService.findById(1L);
        assertNotNull(foundUser);
        assertEquals("John Doe", foundUser.getName());
        verify(userRepository, times(1)).findById(1L);
    }


    @Test
    void testGetAllUsers() {
        List<User> users = Stream.of(userDto).map(userToUserDtoMapper::userDtoToUser).toList();
        when(userRepository.findAll()).thenReturn(users);

        List<UserDto> foundUsers = userService.findAll();

        assertNotNull(foundUsers);
        assertEquals(1, foundUsers.size());
        assertEquals("John Doe", foundUsers.getFirst().getName());
        verify(userRepository, times(1)).findAll();
    }

    @AfterEach
    void closeSession() throws Exception {
        session.close();
    }
}
