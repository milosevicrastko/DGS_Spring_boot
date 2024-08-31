package com.dgs.poc.unitTests;

import com.dgs.poc.data.entities.User;
import com.dgs.poc.data.repositories.UserRepository;
import com.dgs.poc.misc.mappers.UserToUserDtoMapper;
import com.dgs.poc.misc.mappers.UserToUserDtoMapperImpl;
import com.dgs.poc.service.dto.UserDto;
import com.dgs.poc.service.services.implementations.UserServiceImpl;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    private static final String TEST_NAME = "John Doe";
    private static final String TEST_EMAIL = "john.doe@example.com";
    private static final String USER_NOT_FOUND_MESSAGE = "User with id 1 was not found";

    @Spy
    private UserRepository userRepository;

    //Constructor injection not fully supported by mapStruct yet
    @Spy
    private final UserToUserDtoMapper userToUserDtoMapper = new UserToUserDtoMapperImpl();

    @InjectMocks
    private UserServiceImpl userService;

    private UserDto userDto;

    private AutoCloseable session;

    @BeforeEach
    public void setUp() {
        session = MockitoAnnotations.openMocks(this);
        userDto = UserDto.builder().id(1L).name(TEST_NAME).email(TEST_EMAIL).build();
    }


    @Test
    void testSaveUser_shouldSaveUser() {
        User user = userToUserDtoMapper.userDtoToUser(userDto);
        when(userRepository.save(any())).thenReturn(user);
        UserDto savedUserDto = userService.save(userDto);
        assertNotNull(savedUserDto);
        assertEquals(TEST_NAME, savedUserDto.getName());
        assertEquals(TEST_EMAIL, savedUserDto.getEmail());
        verify(userRepository, times(1)).save(userToUserDtoMapper.userDtoToUser(userDto));

    }

    @Test
    void testUpdateUser_shouldUpdateUser() {
        User user = userToUserDtoMapper.userDtoToUser(userDto);
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(any())).thenReturn(user);
        UserDto updatedUser = userService.update(1L, userDto);
        assertNotNull(updatedUser);
        assertEquals(TEST_NAME, updatedUser.getName());
        assertEquals(TEST_EMAIL, updatedUser.getEmail());
        verify(userRepository, times(1)).save(userToUserDtoMapper.userDtoToUser(userDto));

    }

    @Test
    void testUpdateUserNotFound_shouldThrowException() {
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> userService.update(1L, userDto));

        assertEquals(USER_NOT_FOUND_MESSAGE, thrown.getMessage());
    }


    @Test
    void testDeleteUser_shouldDeleteUser() {
        User user = userToUserDtoMapper.userDtoToUser(userDto);
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        userService.deleteById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteUserNotFound_shouldThrowException() {
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> userService.deleteById(1L));

        assertEquals(USER_NOT_FOUND_MESSAGE, thrown.getMessage());
    }


    @Test
    void testGetUserById_shouldReturnUser() {
        User user = userToUserDtoMapper.userDtoToUser(userDto);
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        UserDto foundUser = userService.findById(1L);
        assertNotNull(foundUser);
        assertEquals(TEST_NAME, foundUser.getName());
        verify(userRepository, times(1)).findById(1L);
    }


    @Test
    void testGetAllUsers_shouldReturnAllUsers() {
        List<User> users = Stream.of(userDto).map(userToUserDtoMapper::userDtoToUser).toList();
        when(userRepository.findAll()).thenReturn(users);

        List<UserDto> foundUsers = userService.findAll();

        assertNotNull(foundUsers);
        assertEquals(1, foundUsers.size());
        assertEquals(TEST_NAME, foundUsers.getFirst().getName());
        verify(userRepository, times(1)).findAll();
    }

    @AfterEach
    void closeSession() throws Exception {
        session.close();
    }
}
