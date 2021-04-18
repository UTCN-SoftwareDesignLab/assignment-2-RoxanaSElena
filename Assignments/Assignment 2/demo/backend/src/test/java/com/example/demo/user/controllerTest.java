package com.example.demo.user;

import com.example.demo.BaseControllerTest;
import com.example.demo.TestCreationFactory;
import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.dto.UserListDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.example.demo.TestCreationFactory.*;
import static com.example.demo.UrlMapping.ENTITY;
import static com.example.demo.UrlMapping.USER;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class controllerTest extends BaseControllerTest {
    @InjectMocks
    private UserController controller;

    @Mock
    private UserService userService;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        MockitoAnnotations.openMocks(this);
        controller = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void allUsers() throws Exception {
        List<UserListDTO> userListDTOs = TestCreationFactory.listOf(UserListDTO.class);
        when(userService.allUsersForList()).thenReturn(userListDTOs);

        ResultActions result = mockMvc.perform(get(USER));
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(userListDTOs));
    }

    @Test
    void create() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .username(randomString())
                .email(randomEmail())
                .password(randomString())
                .build();
        when(userService.creat(userDTO)).thenReturn(userDTO);
        ResultActions result = performPostWithRequestBody(USER, userDTO);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(userDTO));
    }

    @Test
    void edit() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .username(randomString())
                .email(randomEmail())
                .password(randomString())
                .build();
        when(userService.edit(userDTO)).thenReturn(userDTO);
        ResultActions result = performPutWithRequestBody(USER, userDTO);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(userDTO));
    }

    @Test
    void delete() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .id(randomLong())
                .username(randomString())
                .password(randomString())
                .build();
        ResultActions result = performDeleteWIthPathVariable(USER+ENTITY, userDTO.getId().toString());
        result.andExpect(status().isOk());
    }
}
