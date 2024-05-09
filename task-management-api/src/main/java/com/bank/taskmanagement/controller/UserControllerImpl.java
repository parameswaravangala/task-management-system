package com.bank.taskmanagement.controller;

import com.bank.taskmanagement.dto.UserDTO;
import com.bank.taskmanagement.entity.User;
import com.bank.taskmanagement.mapper.UserMapper;
import com.bank.taskmanagement.service.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/user")
//@RestController
public class UserControllerImpl {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserControllerImpl(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO save(@RequestBody UserDTO userDTO) {
        User user = userMapper.asEntity(userDTO);
        return userMapper.asDTO(userService.save(user));
    }

    
    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable("id") Integer id) {
        User user = userService.findById(id).orElse(null);
        return userMapper.asDTO(user);
    }

    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userService.deleteById(id);
    }

    
    @GetMapping
    public List<UserDTO> list() {
        return userMapper.asDTOList(userService.findAll());
    }

    
    @GetMapping("/page-query")
    public Page<UserDTO> pageQuery(Pageable pageable) {
        Page<User> userPage = userService.findAll(pageable);
        List<UserDTO> dtoList = userPage
                .stream()
                .map(userMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, userPage.getTotalElements());
    }

    
    @PutMapping("/{id}")
    public UserDTO update(@RequestBody UserDTO userDTO, @PathVariable("id") Integer id) {
        User user = userMapper.asEntity(userDTO);
        return userMapper.asDTO(userService.update(user, id));
    }
}