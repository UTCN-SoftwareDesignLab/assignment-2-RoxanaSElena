package com.example.demo.user;

import com.example.demo.frontoffice.model.Book;
import com.example.demo.frontoffice.model.dto.BookDTO;
import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.dto.UserListDTO;
import com.example.demo.user.dto.UserMinimalDTO;
import com.example.demo.user.mapper.UserMapper;
import com.example.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
    }

    public List<UserMinimalDTO> allUsersMinimal() {
        return userRepository.findAll()
                .stream().map(userMapper::userMinimalFromUser)
                .collect(toList());
    }

    public List<UserListDTO> allUsersForList() {
        return userRepository.findAll()
                .stream().map(userMapper::userListDtoFromUser)
                .collect(toList());
    }

    public UserDTO creat (UserDTO user)
    {
        User u = userMapper.userFromDtoToUser(user);
        u.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(u);
        return user;
    }

    public UserDTO edit (UserDTO user)
    {
        User u = findById(user.getId());
        u.setUsername(user.getUsername());
        u.setEmail(user.getEmail());
        return userMapper.userFromUserToDto(userRepository.save(u));
    }

    public void delete(Long id)
    {
        userRepository.deleteById(id);
    }
}
