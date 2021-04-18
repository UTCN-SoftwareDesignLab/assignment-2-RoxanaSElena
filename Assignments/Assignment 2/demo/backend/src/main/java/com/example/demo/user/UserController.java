package com.example.demo.user;

import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.dto.UserListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.UrlMapping.ENTITY;
import static com.example.demo.UrlMapping.USER;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserListDTO> allUsers() {
        return userService.allUsersForList();
    }

    @PostMapping
    public UserDTO create(@RequestBody UserDTO userDTO) {return userService.creat(userDTO);}

    @PutMapping
    public UserDTO edit(@RequestBody UserDTO userDTO) {return userService.edit(userDTO);}

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) { userService.delete(id);}
}
