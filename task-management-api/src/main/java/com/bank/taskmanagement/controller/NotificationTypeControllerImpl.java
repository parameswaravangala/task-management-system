package com.bank.taskmanagement.controller;

import com.bank.taskmanagement.dto.NotificationTypeDTO;
import com.bank.taskmanagement.entity.NotificationType;
import com.bank.taskmanagement.mapper.NotificationTypeMapper;
import com.bank.taskmanagement.service.NotificationTypeService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/notification-type")
//@RestController
public class NotificationTypeControllerImpl {
    private final NotificationTypeService notificationTypeService;
    private final NotificationTypeMapper notificationTypeMapper;

    public NotificationTypeControllerImpl(NotificationTypeService notificationTypeService, NotificationTypeMapper notificationTypeMapper) {
        this.notificationTypeService = notificationTypeService;
        this.notificationTypeMapper = notificationTypeMapper;
    }

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NotificationTypeDTO save(@RequestBody NotificationTypeDTO notificationTypeDTO) {
        NotificationType notificationType = notificationTypeMapper.asEntity(notificationTypeDTO);
        return notificationTypeMapper.asDTO(notificationTypeService.save(notificationType));
    }

    
    @GetMapping("/{id}")
    public NotificationTypeDTO findById(@PathVariable("id") Integer id) {
        NotificationType notificationType = notificationTypeService.findById(id).orElse(null);
        return notificationTypeMapper.asDTO(notificationType);
    }

    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        notificationTypeService.deleteById(id);
    }

    
    @GetMapping
    public List<NotificationTypeDTO> list() {
        return notificationTypeMapper.asDTOList(notificationTypeService.findAll());
    }

    
    @GetMapping("/page-query")
    public Page<NotificationTypeDTO> pageQuery(Pageable pageable) {
        Page<NotificationType> notificationTypePage = notificationTypeService.findAll(pageable);
        List<NotificationTypeDTO> dtoList = notificationTypePage
                .stream()
                .map(notificationTypeMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, notificationTypePage.getTotalElements());
    }

    
    @PutMapping("/{id}")
    public NotificationTypeDTO update(@RequestBody NotificationTypeDTO notificationTypeDTO, @PathVariable("id") Integer id) {
        NotificationType notificationType = notificationTypeMapper.asEntity(notificationTypeDTO);
        return notificationTypeMapper.asDTO(notificationTypeService.update(notificationType, id));
    }
}