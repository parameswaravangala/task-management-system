package com.bank.taskmanagement.controller;

import com.bank.taskmanagement.dto.UserNotificationPreferenceDTO;
import com.bank.taskmanagement.entity.UserNotificationPreference;
import com.bank.taskmanagement.mapper.UserNotificationPreferenceMapper;
import com.bank.taskmanagement.service.UserNotificationPreferenceService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/user-notification-preference")
//@RestController
public class UserNotificationPreferenceControllerImpl  {
    private final UserNotificationPreferenceService userNotificationPreferenceService;
    private final UserNotificationPreferenceMapper userNotificationPreferenceMapper;

    public UserNotificationPreferenceControllerImpl(UserNotificationPreferenceService userNotificationPreferenceService, UserNotificationPreferenceMapper userNotificationPreferenceMapper) {
        this.userNotificationPreferenceService = userNotificationPreferenceService;
        this.userNotificationPreferenceMapper = userNotificationPreferenceMapper;
    }

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserNotificationPreferenceDTO save(@RequestBody UserNotificationPreferenceDTO userNotificationPreferenceDTO) {
        UserNotificationPreference userNotificationPreference = userNotificationPreferenceMapper.asEntity(userNotificationPreferenceDTO);
        return userNotificationPreferenceMapper.asDTO(userNotificationPreferenceService.save(userNotificationPreference));
    }

    
    @GetMapping("/{id}")
    public UserNotificationPreferenceDTO findById(@PathVariable("id") Integer id) {
        UserNotificationPreference userNotificationPreference = userNotificationPreferenceService.findById(id).orElse(null);
        return userNotificationPreferenceMapper.asDTO(userNotificationPreference);
    }

    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userNotificationPreferenceService.deleteById(id);
    }

    
    @GetMapping
    public List<UserNotificationPreferenceDTO> list() {
        return userNotificationPreferenceMapper.asDTOList(userNotificationPreferenceService.findAll());
    }

    
    @GetMapping("/page-query")
    public Page<UserNotificationPreferenceDTO> pageQuery(Pageable pageable) {
        Page<UserNotificationPreference> userNotificationPreferencePage = userNotificationPreferenceService.findAll(pageable);
        List<UserNotificationPreferenceDTO> dtoList = userNotificationPreferencePage
                .stream()
                .map(userNotificationPreferenceMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, userNotificationPreferencePage.getTotalElements());
    }

    
    @PutMapping("/{id}")
    public UserNotificationPreferenceDTO update(@RequestBody UserNotificationPreferenceDTO userNotificationPreferenceDTO, @PathVariable("id") Integer id) {
        UserNotificationPreference userNotificationPreference = userNotificationPreferenceMapper.asEntity(userNotificationPreferenceDTO);
        return userNotificationPreferenceMapper.asDTO(userNotificationPreferenceService.update(userNotificationPreference, id));
    }
}