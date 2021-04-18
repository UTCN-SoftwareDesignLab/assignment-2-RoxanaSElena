package com.example.demo.user;

import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.dto.UserMinimalDTO;
import com.example.demo.user.mapper.UserMapper;
import com.example.demo.user.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static org.mockito.Mockito.when;


public class ServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository,userMapper,passwordEncoder);
    }

    @Test
    void create()
    {

    }

    @Test
    void edit()
    {

    }

    @Test
    void delete()
    {

    }
}
