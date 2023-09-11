package com.example.employeemanager;

import org.chitsa.projectmanagement.dto.UserDto;
import org.chitsa.projectmanagement.exceptions.UserNotFoundException;
import org.chitsa.projectmanagement.model.User;
import org.chitsa.projectmanagement.repo.UserRepo;
import org.chitsa.projectmanagement.service.UserService;
import org.chitsa.projectmanagement.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepo);
    }

    @Test
    void testRegisterUser() {
        UserDto userDto = new UserDto();
        User user = new User();

        when(userRepo.save(any(User.class))).thenReturn(user);

        assertDoesNotThrow(() -> userService.register(userDto));
    }

    @Test
    void testUpdateUser() {
        Long userId = 1L;
        UserDto userDto = new UserDto();
        User user = new User();

        when(userRepo.findById(userId)).thenReturn(Optional.of(user));
        when(userRepo.save(any(User.class))).thenReturn(user);

        assertDoesNotThrow(() -> userService.update(userId, userDto));
    }

    @Test
    void testLoadByIdUserNotFound() {
        Long userId = 1L;

        when(userRepo.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.loadById(userId));
    }

}
