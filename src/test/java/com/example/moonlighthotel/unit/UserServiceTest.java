package com.example.moonlighthotel.unit;

import com.example.moonlighthotel.exeptions.UserNotFoundException;
import com.example.moonlighthotel.model.User;
import com.example.moonlighthotel.repositories.UserRepository;
import com.example.moonlighthotel.service.UserService;
import com.example.moonlighthotel.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService  = new UserServiceImpl(userRepository);
    }

    @Test
    public void verifyUserRegistration() {

        String password = "1234";

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword(password);

        userRepository.save(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void verifyFindById() {
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.of(User.builder().build()));
        userService.findUserById(id);
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    public void verifyFindUserByIdThrowsException () {
        Long id = 5L;
        String expectedMessage = String.format("User with id %s not found", id);
        UserNotFoundException ex = assertThrows(UserNotFoundException.class,
                () -> userService.findUserById(id));

        assertEquals(expectedMessage, ex.getMessage());
    }


}
