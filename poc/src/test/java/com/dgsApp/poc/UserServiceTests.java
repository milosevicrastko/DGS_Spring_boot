package com.dgsApp.poc;

import com.dgsApp.poc.dataLayer.entities.User;
import com.dgsApp.poc.dataLayer.repositories.UserRepository;
import com.dgsApp.poc.mappers.UserToUserDtoMapper;
import com.dgsApp.poc.mappers.UserToUserDtoMapperImpl;
import com.dgsApp.poc.serviceLayer.dto.UserDto;
import com.dgsApp.poc.serviceLayer.services.implementations.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    private final UserToUserDtoMapper userToUserDtoMapper = new UserToUserDtoMapperImpl();

    @InjectMocks
    private UserServiceImpl userService;

    private UserDto userDto;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName("John Doe");
        userDto.setEmail("john.doe@example.com");
    }


    @Test
    void testSaveUser() {
        userService.save(userDto);
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

    }


    @Test
    void testDeleteUser() {
        userService.deleteById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteUserNotFound() {

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
